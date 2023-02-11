package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.Optional;

public class FullHouse implements Ranking, HandState {
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
