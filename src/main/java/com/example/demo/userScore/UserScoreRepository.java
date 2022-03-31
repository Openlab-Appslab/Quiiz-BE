package com.example.demo.userScore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

    @Query("SELECT u.score FROM UserScore u ")
    UserScore getUserScore();
}
