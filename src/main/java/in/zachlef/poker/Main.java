package in.zachlef.poker;

import in.zachlef.poker.parse.CardParseException;
import in.zachlef.poker.parse.HandParser;
import in.zachlef.poker.rule.Poker;

public class Main {
    public static void main(String[] args) {
//        String hands0 = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";
//        String hands0 = "Black: 2H 3H 4D 5H 2H  White: 2H 3C 4C 5C 7C";
        String hands0 = "Black: 2H 2H 2H 4H 5H  White: 2H 2C 4C 4C 4C";
//        String hands1 = "Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S";
        String hands1 = "Black: 2H 3S 4C 5D 6H  White: 2S 4S 5S 7S 6H";
        String hands2 = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH";
        String hands3 = "Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH";

//        System.out.println(hands0);
        try {
            doGame(hands0);
            doGame(hands1);
        } catch (CardParseException e) {
            e.printStackTrace();
        }
//        doGame(hands1);
//        doGame(hands2);
//        doGame(hands3);
    }

    private static void doGame(String hands) throws CardParseException {

        HandParser handParser = new HandParser();

        Hand blackHand = handParser.parse(hands.split("  ")[0]);
        Hand whiteHand = handParser.parse(hands.split("  ")[1]);

        System.out.println("Black: " + blackHand.toString());
        System.out.println("White: " + whiteHand.toString());


        System.out.println(new Poker().evaluate(blackHand, whiteHand));


    }
}