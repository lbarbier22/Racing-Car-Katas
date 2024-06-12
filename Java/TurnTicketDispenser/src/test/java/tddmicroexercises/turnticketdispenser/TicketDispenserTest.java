package tddmicroexercises.turnticketdispenser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketDispenserTest {

    TicketDispenser dispenser;
    TurnNumberGenerator mockGenerator;

    @BeforeEach
    void init(){
        
        mockGenerator = mock(TurnNumberGenerator.class);
        dispenser = new TicketDispenser(mockGenerator);
    }

    @Test
    void dispenserDistributeATicket() {
        when(mockGenerator.getNextTurnNumber()).thenReturn(0);

        TurnTicket ticket = dispenser.getTurnTicket();

        assertEquals(0, ticket.getTurnNumber());

        verify(mockGenerator, times(1)).getNextTurnNumber();
    }

    @Test
    void dispenserDontDistributeSameTurnNumberTicket() {
        when(mockGenerator.getNextTurnNumber()).thenReturn(0).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);

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

        verify(mockGenerator, times(5)).getNextTurnNumber();
    }

    @Test
    void twoDispensersDontDistributeSameTurnNumberTicket() {
        TicketDispenser dispenser2 = new TicketDispenser(mockGenerator);

        when(mockGenerator.getNextTurnNumber()).thenReturn(0).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4).thenReturn(5);

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

        verify(mockGenerator, times(6)).getNextTurnNumber();
    }
}
