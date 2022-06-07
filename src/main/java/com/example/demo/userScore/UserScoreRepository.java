package com.example.demo.userScore;

import com.example.demo.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

    @Query("SELECT u.score FROM UserScore u ")
    UserScore getUserScore();


    @Query("SELECT u.score FROM UserScore u WHERE u.user.id = :id")
    List<Integer> getScoreForUser(@Param("id") long id);

    @Query("SELECT u FROM UserScore u ")
    List<UserScore> getAllScore();
}
