package in.zachlef.poker;

import in.zachlef.poker.parse.CardParseException;

public enum Suit {
    DIAMOND,
    HEART,
    CLUB,
    SPADE;

    @Override
    public String toString() {
        return "" + this.name().charAt(0);
    }
}
