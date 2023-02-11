package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;

import java.util.Optional;

public class StraightFlush implements HandState, Ranking {

    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        if (new Straight().isSatisfied(hand).isPresent() && new Flush().isSatisfied(hand).isPresent()) {
            return Optional.of(hand);
        } else {
            return Optional.empty();
        }
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
