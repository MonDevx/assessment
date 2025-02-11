package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import com.kbtg.bootcamp.posttest.user.User;
import com.kbtg.bootcamp.posttest.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculateTotalLotteryCostTest {

    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName("Check calculate sum price tickets user")
    void checkCalculateSumPriceTicketsUser() {

        Random rand = new Random();

        User user1 = createUserWithLottery(rand.nextInt(120));
        User user2 = createUserWithLottery(rand.nextInt(120));

        List<User> userList = Arrays.asList(user1, user2);

        Integer calculated = userService.calculateTotalLotteryCost(userList);

        assertThat(calculated).isEqualTo(user1.getLottery().getPrice() + user2.getLottery().getPrice());
    }

    private User createUserWithLottery(int price) {
        Lottery lottery = new Lottery("123456", price, 1);
        return new User(lottery, 1234567890);
    }
}
