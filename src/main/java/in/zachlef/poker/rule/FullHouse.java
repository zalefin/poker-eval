package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.Optional;

public class FullHouse extends HandStateRule {
    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        Optional<Hand> threeHandOptional = new ThreeOfAKind().isSatisfied(hand);
        if (threeHandOptional.isPresent()) {
            Hand threeHand = threeHandOptional.get();
//            Optional<Hand> pairOptional = new Pair().isSatisfied(hand.difference(threeHand));
            if (new Pair().isSatisfied(hand.difference(threeHand)).isPresent()) {
                return threeHandOptional;
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Outcome evaluateDoubleSatisfied(Hand hand0, Hand hand1) {
        return new HighCard().evaluate(hand0, hand1);
    }
}
