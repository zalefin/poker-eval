package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.Optional;

public class Flush extends HandStateRule {

    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        if (hand.getCards().stream().allMatch(card -> card.getSuit() == hand.getCards().get(0).getSuit())) {
            return Optional.of(hand);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Outcome evaluateDoubleSatisfied(Hand hand0, Hand hand1) {
        return new HighCard().evaluate(hand0, hand1);
    }
}
