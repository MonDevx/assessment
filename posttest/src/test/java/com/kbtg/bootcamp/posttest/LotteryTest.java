package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LotteryTest {

    @Test
    void constructorWithArgs() {
        Lottery lottery = new Lottery("123456", 100, 10);
        assertEquals("123456", lottery.getTicket());
        assertEquals(100, lottery.getPrice());
        assertEquals(10, lottery.getAmount());
    }

    @Test
    void constructorWithNoArgs() {
        Lottery lottery = new Lottery();
        assertNull(lottery.getId());
        assertNull(lottery.getTicket());
        assertNull(lottery.getPrice());
        assertNull(lottery.getAmount());
    }

    @Test
    void constructorWithId() {
        Lottery lottery = new Lottery(1, "123456", 100, 10);
        assertEquals(1, lottery.getId());
        assertEquals("123456", lottery.getTicket());
        assertEquals(100, lottery.getPrice());
        assertEquals(10, lottery.getAmount());
    }

    @Test
    void setters() {
        Lottery lottery = new Lottery();
        lottery.setId(1);
        lottery.setTicket("123456");
        lottery.setPrice(100);
        lottery.setAmount(10);
        assertEquals(1, lottery.getId());
        assertEquals("123456", lottery.getTicket());
        assertEquals(100, lottery.getPrice());
        assertEquals(10, lottery.getAmount());
    }
}

