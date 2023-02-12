package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;
import in.zachlef.poker.Suit;
import in.zachlef.poker.Value;
import in.zachlef.poker.parse.CardParseException;
import in.zachlef.poker.parse.HandParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class HighCardTest {

    HandParser parser = new HandParser();

    @Test
    void evaluateWIN() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 2H 2H 4H");
        Hand hand1 = parser.parse(": 2H 2H 2H 2H 3H");

        Outcome outcome = new HighCard().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.WIN);
    }

    @Test
    void evaluateTIE() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 2H 2H 4H");
        Hand hand1 = parser.parse(": 2H 2H 2H 2H 4H");

        Outcome outcome = new HighCard().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.TIE);
    }

    @Test
    void evaluateLOSE() throws CardParseException {
        Hand hand0 = parser.parse(": 2H 2H 2H 2H 3H");
        Hand hand1 = parser.parse(": 2H 2H 2H 2H 4H");

        Outcome outcome = new HighCard().evaluate(hand0, hand1);

        Assert.assertEquals(outcome, Outcome.LOSE);
    }
}