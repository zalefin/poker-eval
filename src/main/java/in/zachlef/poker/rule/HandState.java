package in.zachlef.poker.rule;

import in.zachlef.poker.Hand;

import java.util.Optional;

public interface HandState {
    Optional<Hand> isSatisfied(Hand hand);
}
