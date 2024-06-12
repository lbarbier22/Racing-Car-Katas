package tddmicroexercises.turnticketdispenser;

public class TurnNumberSequence implements TurnNumberGenerator {
    public int turnNumber = 0;

    @Override
    public int getNextTurnNumber() {
        return turnNumber++;
    }
}
