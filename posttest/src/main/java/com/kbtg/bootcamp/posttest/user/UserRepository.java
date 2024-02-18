package com.kbtg.bootcamp.posttest.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByUserID(Integer userID);


    @Query(value="SELECT * FROM user_ticket ut  WHERE ut.userID = :userID and ut.user_lottery = :ticketID limit 1", nativeQuery = true)
    Optional<User> findUserLotteryByUserIDAndLotteryID(@Param("userID") Integer userID, @Param("ticketID") Integer ticketID);
}
