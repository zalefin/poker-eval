package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;

import java.util.Optional;

public class Straight implements Ranking, HandState{

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
    public Outcome evaluate(Hand hand0, Hand hand1) {
        boolean isStraight0 = this.isSatisfied(hand0).isPresent();
        boolean isStraight1 = this.isSatisfied(hand1).isPresent();
        if (!isStraight0 && !isStraight1) {
            return Outcome.TIE;
        } else if (isStraight0 && isStraight1) {
            return new HighCard().evaluate(hand0, hand1);
        } else {
            return isStraight0 ? Outcome.WIN : Outcome.LOSE;
        }
    }
}
