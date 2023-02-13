package in.zachlef.poker;

import java.util.*;

public class Hand {
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
        this.sort();
    }

    public List<Card> getCards() {
        return cards;
    }

    private void sort() {
        // sort the cards from high to low
        cards.sort(Comparator.comparingInt(c0 -> c0.getValue().getRawValue()));
        Collections.reverse(cards);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public Hand difference(Hand otherHand) {
        List<Card> cards = new ArrayList<>();
//        for (Card card : this.cards) {
//            if (!otherHand.getCards().contains(card)) {
//                cards.add(card);
//            }
//        }
        this.cards.forEach(card -> {
            if (!otherHand.getCards().contains(card)) {
                cards.add(card);
            }
        });
//        Collections.copy(cards, this.cards.stream().filter(card -> !otherHand.getCards().contains(card)).toList());
        return new Hand(cards);
    }

    public Card drawRand() {
        int i = new Random().nextInt(this.cards.size());
        Card card = this.getCard(i);
        this.cards.remove(i);
        return card;
    }

    @Override
    public String toString() {
        return String.join(" ", this.cards.stream().map(card -> card.toString()).toList());
    }
}
