package tddmicroexercises.turnticketdispenser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TurnTicketTest {

    @Test
    void verifyTurnTicketWellCreated() {
        TurnTicket turnTicket = new TurnTicket(2);
        assertEquals(2, turnTicket.getTurnNumber());
    }
}
