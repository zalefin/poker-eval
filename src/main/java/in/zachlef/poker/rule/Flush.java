package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

public class Flush implements Ranking {
    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {
        return Outcome.TIE;
    }
}
