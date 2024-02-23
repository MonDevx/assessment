package com.kbtg.bootcamp.posttest;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import com.kbtg.bootcamp.posttest.lottery.TicketsNumberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LotteryServiceTest {

    @InjectMocks
    private LotteryService lotteryService;

    @Mock
    private LotteryRepository lotteryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getLotteries() {
        List<Lottery> lotteries = new ArrayList<>();
        lotteries.add(new Lottery("12345",80,1));
        lotteries.add(new Lottery("67890",80,1));
        when(lotteryRepository.findAll()).thenReturn(lotteries);

        TicketsNumberResponse ticketsNumberResponse = lotteryService.getLotteries();
        assertEquals(2, ticketsNumberResponse.getTickets().length);
        assertEquals("12345", ticketsNumberResponse.getTickets()[0]);
        assertEquals("67890", ticketsNumberResponse.getTickets()[1]);
    }
}
