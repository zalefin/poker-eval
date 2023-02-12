package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ThreeOfAKind extends HandStateRule {

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
            if (cards.size() == 3) {
                return Optional.of(new Hand(cards));
            }
        }
        return Optional.empty();
    }

    @Override
    public Outcome evaluateDoubleSatisfied(Hand hand0, Hand hand1) {
        return new HighCard().evaluate(hand0, hand1);
    }
}
