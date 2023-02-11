package in.zachlef.poker;

import in.zachlef.poker.parse.CardParseException;

public class Value {
    private final int value;
    private final String text;

    public Value(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getRawValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
