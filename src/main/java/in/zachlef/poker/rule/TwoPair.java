package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.Optional;

public class TwoPair implements Ranking {


    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {

        Optional<Hand> pairHighOptional0 = new Pair().isSatisfied(hand0);
        boolean isPairHigh0 = pairHighOptional0.isPresent();
        Optional<Hand> pairHighOptional1 = new Pair().isSatisfied(hand1);
        boolean isPairHigh1 = pairHighOptional1.isPresent();
        if (isPairHigh0 && isPairHigh1) {

            Hand pairHigh0 = pairHighOptional0.get();
            Optional<Hand> pairLowOptional0 = new Pair().isSatisfied(hand0.difference(pairHigh0));
            boolean isPairLow0 = pairLowOptional0.isPresent();

            Hand pairHigh1 = pairHighOptional1.get();
            Optional<Hand> pairLowOptional1 = new Pair().isSatisfied(hand1.difference(pairHigh1));
            boolean isPairLow1 = pairLowOptional1.isPresent();

            if (!isPairLow0 && !isPairLow1) {
                return Outcome.TIE;
            } else if (isPairLow0 && isPairLow1) {
                Outcome pairHighHighCard = new HighCard().evaluate(pairHigh0, pairHigh1);
                if (pairHighHighCard == Outcome.TIE) {
                    Hand pairLow0 = pairLowOptional0.get();
                    Hand pairLow1 = pairLowOptional1.get();
                    Outcome pairLowHighCard = new HighCard().evaluate(pairLow0, pairLow1);
                    if (pairLowHighCard == Outcome.TIE) {
                        Hand remain0 = hand0.difference(pairHigh0).difference(pairLow0);
                        Hand remain1 = hand1.difference(pairHigh1).difference(pairLow1);
                        return new HighCard().evaluate(remain0, remain1);
                    } else {
                        return pairLowHighCard;
                    }
                } else {
                    return pairHighHighCard;
                }

            } else {
                return isPairLow0 ? Outcome.WIN : Outcome.LOSE;
            }

        } else {
            return Outcome.TIE;
        }
    }
}
