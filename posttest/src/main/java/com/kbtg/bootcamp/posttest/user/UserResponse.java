package com.kbtg.bootcamp.posttest.user;


import com.kbtg.bootcamp.posttest.lottery.TicketsNumberResponse;

public class UserResponse extends TicketsNumberResponse {


    private Integer count;

    private Integer cost;

    public UserResponse(String[] tickets, Integer count, Integer cost) {
        super(tickets);
        this.count = count;
        this.cost = cost;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
