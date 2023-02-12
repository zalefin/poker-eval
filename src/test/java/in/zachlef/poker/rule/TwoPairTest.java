package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;
import in.zachlef.poker.parse.CardParseException;
import in.zachlef.poker.parse.HandParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoPairTest {

    HandParser parser = new HandParser();

    @Test
    void evaluateWIN() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 3H 3H 5H");
        Hand hand1 = parser.parse(": 2H 2H 4H 5H 6H");

        Outcome outcome = new TwoPair().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.WIN);
    }

    @Test
    void evaluateBothTIE() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 3H 3H 5H");
        Hand hand1 = parser.parse(": 2H 2H 3H 3H 5H");

        Outcome outcome = new TwoPair().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.TIE);
    }

    @Test
    void evaluateNeitherTIE() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 3H 4H 6H 5H");
        Hand hand1 = parser.parse(": 2H 3H 4H 6H 5H");

        Outcome outcome = new TwoPair().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.TIE);
    }

    @Test
    void evaluateLOSE() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 3H 4H 6H");
        Hand hand1 = parser.parse(": 2H 2H 3H 3H 5H");

        Outcome outcome = new TwoPair().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.LOSE);
    }
}