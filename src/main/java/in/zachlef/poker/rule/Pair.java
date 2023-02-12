package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Pair extends HandStateRule {

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
            if (cards.size() == 2) {
                return Optional.of(new Hand(cards));
            }
        }
        return Optional.empty();
    }

    @Override
    public Outcome evaluateDoubleSatisfied(Hand hand0, Hand hand1) {
        Outcome pairHighCardOutcome = new HighCard().evaluate(hand0, hand1);
        if (pairHighCardOutcome == Outcome.TIE) {
            Hand other0 = hand0.difference(hand0);
            Hand other1 = hand1.difference(hand1);
            Outcome otherHighCardOutcome = new HighCard().evaluate(other0, other1);
            return otherHighCardOutcome;
        } else {
            return pairHighCardOutcome;
        }
    }
}
