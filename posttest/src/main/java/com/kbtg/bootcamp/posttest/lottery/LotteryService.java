package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryService {
    @Autowired
    private  LotteryRepository lotteryRepository;


    public TicketsNumberResponse getLotteries() {
        List<Lottery> lotteries = lotteryRepository.findAll();
        String[] result = lotteries.stream()
                .map(Lottery::getTicket)
                .toArray(String[]::new);

        return new TicketsNumberResponse(result);
    }

}
