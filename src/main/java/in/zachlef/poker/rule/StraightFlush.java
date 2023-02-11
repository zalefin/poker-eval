package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;

import java.util.Optional;

public class StraightFlush implements HandState, Ranking {

    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        if (!hand.getCards().stream().allMatch(card -> card.getSuit() == hand.getCards().get(0).getSuit())) {
            return Optional.empty();
        }
        int last = hand.getCards().get(0).getValue().getRawValue();
        for (Card card : hand.getCards()) {
            if (card.getValue().getRawValue() != last) {
                return Optional.empty();
            }
            last ++;
        }
        return Optional.of(hand);
    }


    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {
        boolean isSF0 = this.isSatisfied(hand0).isPresent();
        boolean isSF1 = this.isSatisfied(hand1).isPresent();
        if (!isSF0 && !isSF1) {
            return Outcome.TIE;
        } else if (isSF0 && isSF1) {
            return new HighCard().evaluate(hand0, hand1);
        } else {
            // because of previous cases, we know ONE of the two must have a straight flush
            return isSF0 ? Outcome.WIN : Outcome.LOSE;
        }
    }
}
