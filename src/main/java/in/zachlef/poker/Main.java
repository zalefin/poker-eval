package in.zachlef.poker;

import in.zachlef.poker.parse.CardParseException;
import in.zachlef.poker.parse.HandParser;
import in.zachlef.poker.rule.Poker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.err.println("Requires path or \"two hand string\" args!");
            System.exit(-1);
        }

        String arg = args[0];
        File file = new File(arg);
        HandParser parser = new HandParser();
        Poker game = new Poker();

        if (file.exists() && file.canRead()) {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String handsLine = reader.nextLine();
                List<Hand> hands;
                try {
                     hands = parser.parseTwoHand(handsLine);
                } catch (CardParseException e) {
                    e.printStackTrace();
                    continue;
                }
                System.out.println(handsLine);
                game.evaluate(hands.get(0), hands.get(1));
            }

        } else {
            List<Hand> hands = null;
            try {
                hands = parser.parseTwoHand(arg);
            } catch (CardParseException e) {
                e.printStackTrace();
                System.exit(-1);
            }
            System.out.println(arg);
            game.evaluate(hands.get(0), hands.get(1));
        }
    }

    public static void doRandomGames(int n) {
        for (int runCount = 0; runCount < n; runCount++) {
            Hand deck = Poker.getDeck();

            List<Card> cards0 = new ArrayList<>();
            List<Card> cards1 = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                cards0.add(deck.drawRand());
                cards1.add(deck.drawRand());
            }

            Hand hand0 = new Hand(cards0);
            Hand hand1 = new Hand(cards1);

            System.out.println("Black: " + hand0);
            System.out.println("White: " + hand1);
            new Poker().evaluate(hand0, hand1);
        }

    }
}