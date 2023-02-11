package in.zachlef.poker.parse;

import in.zachlef.poker.Card;
import in.zachlef.poker.Suit;
import in.zachlef.poker.Value;

public class CardParser implements Parser<Card> {

    private final SuitParser suitParser;
    private final ValueParser valueParser;
    public CardParser() {
        this.suitParser = new SuitParser();
        this.valueParser = new ValueParser();
    }

    @Override
    public Card parse(String token) throws CardParseException {
        if (token.length() != 2) {
            throw new CardParseException("Invalid Card token of length " + token.length());
        }
        Suit suit = suitParser.parse("" + token.charAt(1));
        Value value = valueParser.parse("" + token.charAt(0));
        return new Card(value, suit);
    }

}
