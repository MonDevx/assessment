package com.kbtg.bootcamp.posttest.lottery;

public class TicketsNumberResponse {
    private String[] tickets;
    public TicketsNumberResponse(String[] tickets) {
        this.tickets = tickets;
    }
    public String[] getTickets() {
        return tickets;
    }

    public void setTickets(String[] tickets) {
        this.tickets = tickets;
    }


}