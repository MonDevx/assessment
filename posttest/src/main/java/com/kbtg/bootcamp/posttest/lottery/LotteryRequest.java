package com.kbtg.bootcamp.posttest.lottery;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LotteryRequest {


    @Size(min = 6, message = "Ticket can't less than 6.")
    @Size(max = 6, message = "Ticket can't more than 6.")
    @NotNull(message = "Ticket can't be null.")
    private String ticket;

    @NotNull(message = "Price can't be null.")
    private Integer price;

    @NotNull(message = "Amount can't be null.")
    private Integer amount;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
