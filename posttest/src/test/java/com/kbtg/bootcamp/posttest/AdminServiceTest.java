package com.kbtg.bootcamp.posttest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.kbtg.bootcamp.posttest.admin.AdminService;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryRequest;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private LotteryRepository lotteryRepository;

    @Test
    void createLottery() {
        // Given
        String ticket = "123456";
        int price = 80;
        int amount = 1;
        LotteryRequest lotteryRequest = new LotteryRequest(ticket, price, amount);

        // Mocking repository save
        Lottery expectedLottery = new Lottery(ticket, price, amount);
        when(lotteryRepository.save(any())).thenReturn(expectedLottery);

        // When
        LotteryResponse lotteryResponse = adminService.createLottery(lotteryRequest);

        // Then
        assertNotNull(lotteryResponse);
        assertEquals(ticket, lotteryResponse.ticket());
    }

}
