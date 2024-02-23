package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import com.kbtg.bootcamp.posttest.user.User;
import com.kbtg.bootcamp.posttest.user.UserRepository;
import com.kbtg.bootcamp.posttest.user.UserResponse;
import com.kbtg.bootcamp.posttest.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LotteryRepository lotteryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getLotteriesByUserID() {
        boolean thrown = false;
        try {
            List<User> userLotteries = new ArrayList<>();
            when(userRepository.findAllByUserID(1)).thenReturn(userLotteries);
            UserResponse userResponse = userService.getLotteriesByUserID(1);
        } catch (NotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void findUserLotteriesByUserID() {
        boolean thrown = false;
        try {
        List<User> userLotteries = new ArrayList<>();
        when(userRepository.findAllByUserID(1)).thenReturn(userLotteries);

        List<User> result = userService.findUserLotteriesByUserID(1);
        } catch (NotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void findLotteryById() {
        Lottery lottery = new Lottery();
        when(lotteryRepository.findById(1)).thenReturn(Optional.of(lottery));

        Lottery result = userService.findLotteryById(1);
        assertEquals(lottery, result);
    }

    @Test
    void createUserWithLottery() {
        Lottery lottery = new Lottery(null,null,null);
        User user = new User(lottery, 1);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUserWithLottery(1, lottery);
        assertEquals(user.getId(), result.getId());
    }

    @Test
    void deleteUserTicketsByUserIdAndTicket() {
        User userLottery = new User(new Lottery(), 1);
        when(userRepository.findUserLotteryByUserIDAndLotteryID(1, 1)).thenReturn(Optional.of(userLottery));

        LotteryResponse lotteryResponse = userService.deleteUserTicketsByUserIdAndTicket(1, 1);
        assertEquals(userLottery.getLottery().getTicket(), lotteryResponse.ticket());
    }
}
