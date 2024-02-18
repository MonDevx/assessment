package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryController;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import com.kbtg.bootcamp.posttest.lottery.TicketsNumberResponse;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LotteryControllerTests {
    MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        LotteryController walletController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(walletController)
                .build();
    }

    @Test
    @DisplayName("when perform on GET: /lotteries should return 123456 and 123457")
    void viewLotteries() throws Exception {
        setupMockService();

        mockMvc.perform(get("/lotteries"))
                .andExpect(jsonPath("$['tickets'].length()", is(2)))
                .andExpect(jsonPath("$['tickets'][0]", is("123456")))
                .andExpect(jsonPath("$['tickets'][1]", is("123457")))
                .andExpect(status().isOk());
    }

    private void setupMockService() {
        Lottery lottery1 = createLottery("123456", 80, 1);
        Lottery lottery2 = createLottery("123457", 80, 1);

        String[] result = Stream.of(lottery1, lottery2)
                .map(Lottery::getTicket)
                .toArray(String[]::new);

        when(lotteryService.getLotteries()).thenReturn(new TicketsNumberResponse(result));
    }

    private Lottery createLottery(String ticket, int price, int amount) {
        return new Lottery(ticket, price, amount);
    }

}
