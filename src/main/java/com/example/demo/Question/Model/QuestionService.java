package com.example.demo.Question.Model;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;

import java.util.List;

public interface QuestionService {

    List<QuestionDto> saveQuestions(List<Question> questions);

    List<QuestionDto> findAll();

    List<QuestionDto> getQuestionByID();

    List<QuestionDto> questionsByQuizName(String quizName);

    List<QuestionDto> getAllQuestionsForQuiz();
}
