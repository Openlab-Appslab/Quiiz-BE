package com.example.demo.answer.Model;

import com.example.demo.answer.Answer;

import java.util.List;

public interface AnswerService {

    void saveAnswer(Answer answer);

    List<Answer> readAnswers(List<Answer> answerList);
}
