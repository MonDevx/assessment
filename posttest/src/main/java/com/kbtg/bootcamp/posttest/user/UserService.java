package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private  UserRepository userRepository;

    private  LotteryRepository lotteryRepository;
    @Autowired
    public UserService(UserRepository userRepository, LotteryRepository lotteryRepository) {
        this.userRepository = userRepository;
        this.lotteryRepository = lotteryRepository;
    }

    public UserResponse getLotteriesByUserID(Integer userId) {
        List<User> userLotteries = findUserLotteriesByUserID(userId);

        String[] lotteries = getLotteryTickets(userLotteries);
        Integer count = lotteries.length;

        Integer cost = calculateTotalLotteryCost(userLotteries);

        return new UserResponse(lotteries, count, cost);
    }

    public List<User> findUserLotteriesByUserID(Integer userId) {
        List<User> userLotteries = userRepository.findAllByUserID(userId);
        if (userLotteries.isEmpty()) {
            throw new NotFoundException("User not found.");
        }
        return userLotteries;
    }

    public String[] getLotteryTickets(List<User> userLotteries) {
        return userLotteries.stream()
                .map(User::getLottery)
                .map(Lottery::getTicket)
                .toArray(String[]::new);
    }

    public Integer calculateTotalLotteryCost(List<User> userLotteries) {
        return userLotteries.stream()
                .map(User::getLottery)
                .map(Lottery::getPrice)
                .reduce(0, Integer::sum);
    }

    public User createLotteryUserByUserID(Integer userId, Integer ticketId) {

        Lottery lottery = findLotteryById(ticketId);

        return createUserWithLottery(userId, lottery);
    }



    public Lottery findLotteryById(Integer ticketId) {
        return lotteryRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Lottery not found."));
    }

    public User createUserWithLottery(Integer userId, Lottery lottery) {

        User user = new User(lottery, userId);
        userRepository.save(user);
        return new User(user.getId());
    }

    public LotteryResponse deleteUserTicketsByUserIdAndTicket(Integer userId, Integer ticketId) {
        User userLottery = findUserLotteryByUserIDAndLotteryID(userId, ticketId);

        userRepository.deleteById(userLottery.getId());

        return new LotteryResponse(userLottery.getLottery().getTicket());
    }

    public User findUserLotteryByUserIDAndLotteryID(Integer userId, Integer ticketId) {
        return userRepository.findUserLotteryByUserIDAndLotteryID(userId, ticketId)
                .orElseThrow(() -> new NotFoundException("User or lottery not found."));
    }

}
