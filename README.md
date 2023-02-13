# Poker Hand Evaluator
A Poker hand evaluator that will determine the winner between two Poker hands.

## How to Build
1. Install Gradle
2. Install Java 17 JDK
3. Run `./gradlew build` in your command prompt
4. Run `./gradlew run --args="hands.txt"` in your command prompt
    - Alternatively, the executable jar can be run directly by running `java -jar ./build/libs/manifestsoln-kata-poker-1.0-SNAPSHOT.jar "hands.txt"`

## How to Use
The program expects a command line argument containing either a valid "two hand string" or a file path.

A valid "two hand string" contains the name of the hand and the card values, separated by spaces.
A sample could look like the following:
```
Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH
```
It's important to note that the two hands must be separated by *two* spaces in the middle,
even though each card ought to only be separated by *one* space.

You can also put multiple "two hand strings", separated by a new line, in a text file to read.

Every card substring must consist of a valid **value:** `2..9,T,J,Q,K,A`, and **suit:** `D,H,C,S`.

**Note:** The output (win, lose, tie) is always with respect to the first hand defined.

## Strategy
The Poker hand evaluator checks the following rules in the order listed:
- **Straight flush:** 5 cards of the same suit with consecutive values. Ranked by the highest card in the hand.
- **Four of a kind:** 4 cards with the same value. Ranked by the value of the 4 cards.
- **Full House:** 3 cards of the same value, with the remaining 2 cards forming a pair. Ranked by the value of the 3 cards.
- **Flush:** Hand contains 5 cards of the same suit. Hands which are both flushes are ranked using the rules for High Card.
- **Straight:** Hand contains 5 cards with consecutive values. Hands which both contain a straight are ranked by their highest card.
- **Three of a Kind:** Three of the cards in the hand have the same value. Hands which both contain three of a kind are ranked by the value of the 3 cards.
- **Two Pairs:** The hand contains 2 different pairs. Hands which both contain 2 pairs are ranked by the value of their highest pair. Hands with the same highest pair are ranked by the value of their other pair. If these values are the same the hands are ranked by the value of the remaining card.
- **Pair:** 2 of the 5 cards in the hand have the same value. Hands which both contain a pair are ranked by the value of the cards forming the pair. If these values are the same, the hands are ranked by the values of the cards not forming the pair, in decreasing order.
- **High Card:** Hands which do not fit any higher category are ranked by the value of their highest card. If the highest cards have the same value, the hands are ranked by the next highest, and so on.



> This project was taken from https://codingdojo.org/kata/PokerHands/