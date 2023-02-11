package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.ArrayList;
import java.util.List;

public class Poker implements Ranking {

    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {
        List<Ranking> steps = new ArrayList<>();
        steps.add(new StraightFlush());
        steps.add(new FourOfAKind());
        steps.add(new FullHouse());
        steps.add(new Flush());
        steps.add(new Straight());
        steps.add(new ThreeOfAKind());
        steps.add(new TwoPair());
        steps.add(new Pair());
        steps.add(new HighCard());

        for (Ranking step : steps) {
            Outcome outcome = step.evaluate(hand0, hand1);
            if (outcome != Outcome.TIE) {
                System.out.println("" + outcome + " @ " + step.getClass().getName());
                return outcome;
            }
        }

        return Outcome.TIE;
    }
}
