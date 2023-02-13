package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;
import in.zachlef.poker.Suit;
import in.zachlef.poker.Value;

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

        System.out.println("" + Outcome.TIE + " @ " + HighCard.class.getName());
        return Outcome.TIE;
    }

    public static Hand getDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <= 14; i++) {
                Value value = Value.fromInt(i);
                cards.add(new Card(value, suit));
            }
        }
        return new Hand(cards);
    }
}
