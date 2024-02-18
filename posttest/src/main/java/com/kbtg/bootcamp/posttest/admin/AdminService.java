package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryRequest;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final LotteryRepository lotteryRepository;

    public AdminService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public LotteryResponse createLottery(LotteryRequest lotteryRequest){

        Lottery lottery = new Lottery(lotteryRequest.getTicket(),lotteryRequest.getPrice(),lotteryRequest.getAmount());
        lotteryRepository.save(lottery);

        return new LotteryResponse(lottery.getTicket());
    }


}
