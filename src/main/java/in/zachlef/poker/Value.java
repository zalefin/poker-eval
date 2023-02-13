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

    public static Value fromInt(int val) {
        String text;
        switch (val) {
            case 14:
                text = "A";
                break;
            case 13:
                text = "K";
                break;
            case 12:
                text = "Q";
                break;
            case 11:
                text = "J";
                break;
            case 10:
                text = "T";
                break;
            default:
                text = "" + val;

        }

        return new Value(val, text);
    }

}
