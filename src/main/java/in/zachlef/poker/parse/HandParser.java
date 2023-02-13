package in.zachlef.poker.parse;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandParser implements Parser<Hand> {
    public Hand parse(String handLine) throws CardParseException {
        final CardParser cardParser = new CardParser();
        List<Card> cards = new ArrayList<>();
        for (String token : Arrays.stream(handLine.split(" ")).skip(1).toList()) {
            cards.add(cardParser.parse(token));
        }

        if (cards.size() != 5) {
            throw new CardParseException("Invalid Card count: " + cards.size());
        }

        return new Hand(cards);
    }

    public List<Hand> parseTwoHand(String handsLine) throws CardParseException {
        String[] handLines = handsLine.split("  ");
        List<Hand> hands = new ArrayList<>();
        hands.add(parse(handLines[0]));
        hands.add(parse(handLines[1]));
        return hands;
    }
}
