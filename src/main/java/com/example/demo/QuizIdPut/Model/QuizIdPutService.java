package com.example.demo.QuizIdPut.Model;

import com.example.demo.Quiz.Quiz;
import com.example.demo.QuizIdPut.QuizIdPut;

public interface QuizIdPutService {

    void replaceQuizId(String quizIdPut);

    void saveQuizId(QuizIdPut quizIdPut);

    String getQuizId();
}
