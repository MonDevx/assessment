package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import com.kbtg.bootcamp.posttest.user.User;
import com.kbtg.bootcamp.posttest.user.UserController;
import com.kbtg.bootcamp.posttest.user.UserResponse;
import com.kbtg.bootcamp.posttest.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    MockMvc mockMvc;
    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        UserController userController = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    @DisplayName("when perform on GET: /1234567890/lotteries should return 123456 and 123457")
    void getLotteriesByUserID() throws Exception {
        String[] result = setupMockService();
        when(userService.getLotteriesByUserID(any())).thenReturn(new UserResponse(result, result.length, 160));
        mockMvc.perform(get("/users/1234567890/lotteries"))
                .andExpect(jsonPath("$['tickets'].length()", is(2)))
                .andExpect(jsonPath("$['tickets'][0]", is("123456")))
                .andExpect(jsonPath("$['tickets'][1]", is("123457")))
                .andExpect(jsonPath("$['count']", is(2)))
                .andExpect(jsonPath("$['cost']", is(160)))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("when perform on post: /users/1234567890/lotteries/1 should return 1 ")
    void createLotteriesByUserIDAndTicketID() throws Exception {
        Lottery lottery1 = createLottery(1,"123456", 80, 1);
        when(userService.createLotteryUserByUserID(any(),any())).thenReturn(new User(lottery1.getId()));
        mockMvc.perform(post("/users/1234567890/lotteries/1"))
                .andExpect(jsonPath("$['id']", is(lottery1.getId())))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("when perform on delete: /users/{userId}/lotteries/{ticketId} should return 123456")
    void deleteLotteriesByUserIDAndTicketID() throws Exception {
        Lottery lottery1 = createLottery(1,"123456", 80, 1);
        when(userService.deleteUserTicketsByUserIdAndTicket(any(),any())).thenReturn(new LotteryResponse(lottery1.getTicket()));

        mockMvc.perform(delete("/users/1234567890/lotteries/1"))
                .andExpect(jsonPath("$['ticket']", is(lottery1.getTicket())))
                .andExpect(status().isOk());
    }

    private String[] setupMockService() {
        Lottery lottery1 = createLottery(1,"123456", 80, 1);
        Lottery lottery2 = createLottery(2,"123457", 80, 1);

        String[] result = Stream.of(lottery1, lottery2)
                .map(Lottery::getTicket)
                .toArray(String[]::new);

        return result;
    }

    private Lottery createLottery(Integer id, String ticket, int price, int amount) {
        return new Lottery(id,ticket, price, amount);
    }

}
