package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class FourOfAKind implements HandState, Ranking {

    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        HashMap<Integer, List<Card>> valueMap = new HashMap<>();
        for (Card card : hand.getCards()) {
            int value = card.getValue().getRawValue();
            if (!valueMap.containsKey(value)) {
                valueMap.put(value, new ArrayList<>());
            }
            List<Card> cards = valueMap.get(value);
            cards.add(card);
            if (cards.size() == 4) {
                return Optional.of(new Hand(cards));
            }
        }
        return Optional.empty();
    }

    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {
        // TODO rename vars here
        Optional<Hand> pairOptional0 = this.isSatisfied(hand0);
        boolean isPair0 = pairOptional0.isPresent();
        Optional<Hand> pairOptional1 = this.isSatisfied(hand1);
        boolean isPair1 = pairOptional1.isPresent();

        if (!isPair0 && !isPair1) {
            return Outcome.TIE;
        } else if (isPair0 && isPair1) {
            Hand pair0 = pairOptional0.get();
            Hand pair1 = pairOptional1.get();
            Outcome pairHighCardOutcome = new HighCard().evaluate(pair0, pair1);
            return pairHighCardOutcome;
        } else {
            return isPair0 ? Outcome.WIN : Outcome.LOSE;
        }
    }
}
