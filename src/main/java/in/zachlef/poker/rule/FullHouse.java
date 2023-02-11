package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.Optional;

public class FullHouse implements Ranking, HandState {
    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        return Optional.empty();
    }

    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {
        return Outcome.TIE;
    }
}
