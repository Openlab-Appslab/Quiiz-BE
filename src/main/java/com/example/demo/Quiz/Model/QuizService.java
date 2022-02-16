package com.example.demo.Quiz.Model;

import com.example.demo.Quiz.Quiz;

import java.util.List;

public interface QuizService {

    void saveQuiz(Quiz quiz);

    List<Quiz> readQuiz(Quiz quiz);
}
