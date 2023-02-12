package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.Optional;

public abstract class HandStateRule implements Ranking, HandState {

    public abstract Outcome evaluateDoubleSatisfied(Hand hand0, Hand hand1);

    @Override
    public Outcome evaluate(Hand hand0, Hand hand1) {
        Optional<Hand> handOptional0 = this.isSatisfied(hand0);
        boolean isSatisfied0 = handOptional0.isPresent();
        Optional<Hand> handOptional1 = this.isSatisfied(hand1);
        boolean isSatisfied1 = handOptional1.isPresent();

        if (!isSatisfied0 && !isSatisfied1) {
            // This is the case in which neither hand state is satisfied, we will return a TIE.
            return Outcome.TIE;
        } else if (isSatisfied0 && isSatisfied1) {
            // This is the case in which both hand states are satisfied, we will return
            // the result of the tie-breaker.
            // This may be any Outcome
            Hand satisfiedHand0 = handOptional0.get();
            Hand satisfiedHand1 = handOptional1.get();
            return this.evaluateDoubleSatisfied(satisfiedHand0, satisfiedHand1);
        } else {
            // This is the case in which only one of the hands are satisfied, in which case we
            // return WIN or LOSE depending on which it is.
            return isSatisfied0 ? Outcome.WIN : Outcome.LOSE;
        }

    }
}
