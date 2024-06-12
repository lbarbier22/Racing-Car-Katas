package tddmicroexercises.turnticketdispenser;

public class TurnNumberSequence
{
    public static int turnNumber = 0;

    public static int getNextTurnNumber()
    {
        return turnNumber++;
    }
}
