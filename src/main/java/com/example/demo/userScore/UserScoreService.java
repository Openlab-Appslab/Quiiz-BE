package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;

import java.util.List;


public interface UserScoreService {
    void saveScore(Quiz quiz, Integer score);
    Integer getAllScore();
    Integer getAllScoreByQuiz(String quizId);

    List<UserScoreDto> getAllScoreForAllQuiz();

    List<AllScoreDto> getAllScoreForAllUser();

    List<UserScoreDto> getScoreForUser();

    Integer getScoreForCurrentQuiz(String quizId, Integer score);

    void resetCurrentScore(String quizId);
}
