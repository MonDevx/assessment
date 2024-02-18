package com.kbtg.bootcamp.posttest.lottery;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class LotteryRequest {


    @Size(min = 6, message = "Ticket can't less than 6.")
    @Size(max = 6, message = "Ticket can't more than 6.")
    @NotNull(message = "Ticket can't be null.")
    private String ticket;

    @Positive(message = "Price can't negative number.")
    @NotNull(message = "Price can't be null.")
    private Integer price;
    @Positive(message = "Amount can't negative number.")
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

    public LotteryRequest(String ticket, Integer price, Integer amount) {
        this.ticket = ticket;
        this.price = price;
        this.amount = amount;
    }
}
