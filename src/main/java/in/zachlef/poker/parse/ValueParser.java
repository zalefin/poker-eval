package in.zachlef.poker.parse;

import in.zachlef.poker.Value;

public class ValueParser implements Parser<Value> {
    @Override
    public Value parse(String token) throws CardParseException {
        // first check if the value is a "letter card"
        switch (token) {
            case "A":
                return new Value(14, token);
            case "K":
                return new Value(13, token);
            case "Q":
                return new Value(12, token);
            case "J":
                return new Value(11, token);
            case "T":
                return new Value(10, token);
        }

        int val;
        try {
            val = Integer.parseInt(token);
            if (val < 2 || val > 9) {
                throw new CardParseException("Invalid letter or number value: " + token);
            }
        } catch (NumberFormatException e) {
            throw new CardParseException("Invalid letter or number value: " + token);
        }

        return new Value(val, token);
    }
}
