package com.kbtg.bootcamp.posttest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.bootcamp.posttest.admin.AdminController;
import com.kbtg.bootcamp.posttest.admin.AdminService;
import com.kbtg.bootcamp.posttest.lottery.LotteryRequest;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class AdminControllerTests {
    MockMvc mockMvc;
    @Mock
    AdminService adminService;

    @BeforeEach
    void setUp() {
        AdminController adminController = new AdminController(adminService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .build();
    }

    @Test
    @DisplayName("when perform on POST: /admin/lotteries should return ticket 123456")
    void createLotteries() throws Exception {
        LotteryRequest lottery = createLottery("123456", 80, 1);
        when(adminService.createLottery(any())).thenReturn(new LotteryResponse(lottery.getTicket()));
        mockMvc.perform(
                        post("/admin/lotteries")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(lottery))
                )
                .andExpect(jsonPath("$['ticket']", is(lottery.getTicket())))
                .andExpect(status().isCreated());
    }



    private LotteryRequest createLottery(String ticket, int price, int amount) {
        return new LotteryRequest(ticket, price, amount);
    }

}
