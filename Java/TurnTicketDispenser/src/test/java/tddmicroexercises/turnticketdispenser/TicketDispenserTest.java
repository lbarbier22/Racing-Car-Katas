package tddmicroexercises.turnticketdispenser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketDispenserTest {

    TicketDispenser dispenser;

    @BeforeEach
    void init(){
        dispenser = new TicketDispenser();
    }

    @Test
    void DispenserDistributeATicket() {
        TurnTicket ticket = dispenser.getTurnTicket();
        assertEquals(0, ticket.getTurnNumber());
    }

    @Test
    void DispenserDontDistributeSameTurnNumberTicket() {
        TurnTicket ticket1 = dispenser.getTurnTicket();
        TurnTicket ticket2 = dispenser.getTurnTicket();
        TurnTicket ticket3 = dispenser.getTurnTicket();
        TurnTicket ticket4 = dispenser.getTurnTicket();
        TurnTicket ticket5 = dispenser.getTurnTicket();

        assertEquals(0, ticket1.getTurnNumber());
        assertEquals(1, ticket2.getTurnNumber());
        assertEquals(2, ticket3.getTurnNumber());
        assertEquals(3, ticket4.getTurnNumber());
        assertEquals(4, ticket5.getTurnNumber());
    }

    @Test
    void TwoDispensersDontDistributeSameTurnNumberTicket() {
        TicketDispenser dispenser2 = new TicketDispenser();

        TurnTicket ticket1 = dispenser.getTurnTicket();
        TurnTicket ticket2 = dispenser.getTurnTicket();

        TurnTicket ticket3 = dispenser2.getTurnTicket();

        TurnTicket ticket4 = dispenser.getTurnTicket();

        TurnTicket ticket5 = dispenser2.getTurnTicket();
        TurnTicket ticket6 = dispenser2.getTurnTicket();

        assertEquals(0, ticket1.getTurnNumber());
        assertEquals(1, ticket2.getTurnNumber());
        assertEquals(2, ticket3.getTurnNumber());

        assertEquals(3, ticket4.getTurnNumber());
        assertEquals(4, ticket5.getTurnNumber());
        assertEquals(5, ticket6.getTurnNumber());
    }

}
