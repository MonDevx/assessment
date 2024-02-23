package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.user.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserResponseTest {

    private UserResponse userResponse;

    @BeforeEach
    public void setUp() {
        String[] tickets = {"ticket1", "ticket2"};
        userResponse = new UserResponse(tickets, 2, 100);
    }

    @Test
    public void testConstructor() {
        assertNotNull(userResponse);
        assertArrayEquals(new String[]{"ticket1", "ticket2"}, userResponse.getTickets());
        assertEquals(2, userResponse.getCount());
        assertEquals(100, userResponse.getCost());
    }

    @Test
    public void testGettersAndSetters() {
        userResponse.setCount(3);
        userResponse.setCost(150);
        assertArrayEquals(new String[]{"ticket1", "ticket2"}, userResponse.getTickets());
        assertEquals(3, userResponse.getCount());
        assertEquals(150, userResponse.getCost());
    }

    @Test
    public void testGetTickets() {
        assertArrayEquals(new String[]{"ticket1", "ticket2"}, userResponse.getTickets());
    }
}
