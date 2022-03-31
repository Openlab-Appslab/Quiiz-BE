package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;
import com.example.demo.score.ScoreDto;
import com.example.demo.user.User;
import org.springframework.stereotype.Service;


public interface UserScoreService {
    UserScore saveScore(Quiz quiz, Integer score);
}
