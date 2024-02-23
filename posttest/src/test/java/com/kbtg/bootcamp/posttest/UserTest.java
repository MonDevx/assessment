package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserTest {

    @Test
    void constructorWithArgs() {
        Lottery lottery = new Lottery();
        lottery.setId(1);
        User user = new User(lottery, 1234567890);
        assertEquals(lottery, user.getLottery());
        assertEquals(1234567890, user.getUserID());
    }

    @Test
    void constructorWithId() {
        User user = new User(1);
        assertEquals(1, user.getId());
    }

    @Test
    void setters() {
        Lottery lottery = new Lottery();
        lottery.setId(1);
        User user = new User();
        user.setId(1);
        user.setLottery(lottery);
        user.setUserID(1234567890);
        assertEquals(1, user.getId());
        assertEquals(lottery, user.getLottery());
        assertEquals(1234567890, user.getUserID());
    }
}
