package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;
import in.zachlef.poker.parse.CardParseException;
import in.zachlef.poker.parse.HandParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FlushTest {
    HandParser parser = new HandParser();

    @Test
    void isSatisfiedSome() throws CardParseException {
        Hand hand = parser.parse(": 2H 4H 6H 8H TH");

        Optional<Hand> handOptional = new Flush().isSatisfied(hand);

        Assert.assertTrue(handOptional.isPresent());
        Assert.assertEquals(handOptional.get().getCards().size(), 5);
    }

    @Test
    void isSatisfiedNone() throws CardParseException {
        Hand hand = parser.parse(": 2H 3H 4H 5H 5C");

        Optional<Hand> handOptional = new Flush().isSatisfied(hand);

        Assert.assertTrue(handOptional.isEmpty());
    }

    @Test
    void evaluateWIN() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 2H 4H 5H");
        Hand hand1 = parser.parse(": 2H 3H 4H 5H 6C");

        Outcome outcome = new Flush().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.WIN);
    }

    @Test
    void evaluateBothTIE() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 2H 4H 5H");
        Hand hand1 = parser.parse(": 2H 2H 2H 4H 5H");

        Outcome outcome = new Flush().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.TIE);
    }

    @Test
    void evaluateNeitherTIE() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 3H 4H 6H 5C");
        Hand hand1 = parser.parse(": 2H 3H 4H 6H 5C");

        Outcome outcome = new Flush().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.TIE);
    }

    @Test
    void evaluateLOSE() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 2H 4H 6C");
        Hand hand1 = parser.parse(": 2H 3H 4H 5H 5H");

        Outcome outcome = new Flush().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.LOSE);
    }
}