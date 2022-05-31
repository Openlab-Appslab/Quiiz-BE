package com.example.demo.Quiz.Model;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import com.example.demo.Quiz.Quiz;
import com.example.demo.Quiz.QuizDto;

import java.util.List;

public interface QuizService {

    void saveQuiz(Quiz quiz);

    List<QuizDto> readQuizzes();

    List<String> getAllQuizNames();

    String getQuizName(String quizName);

    void favoriteFalse(Quiz quiz);
    void favoriteTrue(Quiz quiz);

}
