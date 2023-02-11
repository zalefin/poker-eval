package in.zachlef.poker.parse;

public interface Parser<T> {
    T parse(String token) throws CardParseException;
}
