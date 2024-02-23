package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.lottery.TicketsNumberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketsNumberResponseTest {

    private TicketsNumberResponse ticketsNumberResponse;

    @BeforeEach
    public void setUp() {
        String[] tickets = {"ticket1", "ticket2"};
        ticketsNumberResponse = new TicketsNumberResponse(tickets);
    }

    @Test
    public void testConstructor() {
        assertNotNull(ticketsNumberResponse);
        assertArrayEquals(new String[]{"ticket1", "ticket2"}, ticketsNumberResponse.getTickets());
    }

    @Test
    public void testGettersAndSetters() {
        ticketsNumberResponse.setTickets(new String[]{"ticket3", "ticket4"});
        assertArrayEquals(new String[]{"ticket3", "ticket4"}, ticketsNumberResponse.getTickets());
    }
}
