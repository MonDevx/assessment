package com.kbtg.bootcamp.posttest.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "user_ticket")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // If we found an error try to replace this with GenerationType.IDENTITY
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_lottery")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Lottery lottery;

    @Column(name = "userID",length = 10)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer userID;

    public User(){}
    public User(Lottery lottery, Integer userID) {
        this.lottery = lottery;
        this.userID = userID;
    }
    public User(Integer id) {
        this.id = id;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }


}
