package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

public class HighCard implements Ranking {
    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {
        for (int i = 0; i < hand0.getCards().size(); i++) {
            int v0 = hand0.getCard(i).getValue().getRawValue();
            int v1 = hand1.getCard(i).getValue().getRawValue();
            if (v0 > v1) {
                return Outcome.WIN;
            } else if (v0 < v1) {
                return Outcome.LOSE;
            }
        }

        return Outcome.TIE;
    }
}
