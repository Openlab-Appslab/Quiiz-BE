package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;

import java.util.List;


public interface UserScoreService {
    UserScore saveScore(Quiz quiz, Integer score);

    List<UserScoreDto> getAllScore();
}
