package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;

import java.util.Optional;

public class Straight extends HandStateRule {

    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        int last = hand.getCards().get(0).getValue().getRawValue();
        for (Card card : hand.getCards()) {
            if (card.getValue().getRawValue() != last) {
                return Optional.empty();
            }
            // decrement the value to check for consecutive values
            last --;
        }
        return Optional.of(hand);
    }

    @Override
    public Outcome evaluateDoubleSatisfied(Hand hand0, Hand hand1) {
        return new HighCard().evaluate(hand0, hand1);
    }
}
