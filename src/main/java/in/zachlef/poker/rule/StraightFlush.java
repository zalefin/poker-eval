package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.Optional;

public class StraightFlush extends HandStateRule {

    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        if (new Straight().isSatisfied(hand).isPresent() && new Flush().isSatisfied(hand).isPresent()) {
            return Optional.of(hand);
        } else {
            return Optional.empty();
        }
    }


    @Override
    public Outcome evaluateDoubleSatisfied(Hand hand0, Hand hand1) {
        return new HighCard().evaluate(hand0, hand1);
    }
}
