package in.zachlef.poker.rule;

import in.zachlef.poker.Card;
import in.zachlef.poker.Hand;
import in.zachlef.poker.Suit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FourOfAKind implements HandState, Ranking {
    @Override
    public Optional<Hand> isSatisfied(Hand hand) {
        List<Card> foakCards;
        for (Suit suit : Suit.values()) {
            foakCards = new ArrayList<>();
            int count = 0;
            for (Card card : hand.getCards()) {
                if (card.getSuit() == suit) {
                    foakCards.add(card);
                    count ++;
                }
            }
            if (count >= 4) {
                return Optional.of(new Hand(foakCards));
            }
        }
        return Optional.empty();
    }

    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {
        Optional<Hand> foakOpt0 = isSatisfied(hand0);
        boolean isFoak0 = foakOpt0.isPresent();
        Optional<Hand> foakOpt1 = isSatisfied(hand1);
        boolean isFoak1 = foakOpt1.isPresent();
        if (!isFoak0 && !isFoak1) {
            return Outcome.TIE;
        } else if (isFoak0 && isFoak1) {
            return new HighCard().evaluate(foakOpt0.get(), foakOpt1.get());
        } else {
            // because of previous cases, we know ONE of the two must have a straight flush
            return isFoak0 ? Outcome.WIN : Outcome.LOSE;
        }
    }
}
