package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "lottery")
@Data
public class Lottery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // If we found an error try to replace this with GenerationType.IDENTITY
    private Integer id;
    @Column(length = 6)
    private String ticket;

    private Integer price;
    private Integer amount;

    public Lottery() {
    }

    public Lottery(String ticket, Integer price, Integer amount) {
        this.ticket = ticket;
        this.price = price;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
