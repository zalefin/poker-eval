package in.zachlef.poker.parse;

import in.zachlef.poker.Suit;

public class SuitParser implements Parser<Suit> {
    @Override
    public Suit parse(String token) throws CardParseException {
        switch (token) {
            case "D":
                return Suit.DIAMOND;
            case "H":
                return Suit.HEART;
            case "C":
                return Suit.CLUB;
            case "S":
                return Suit.SPADE;
            default:
                throw new CardParseException("Invalid Suit token " + token);
        }
    }
}
