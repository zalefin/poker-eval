package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

public interface Ranking {
    Outcome evaluate(Hand hand0, Hand hand1);
}
