package com.example.demo.answer.Model;

import com.example.demo.Question.Question;
import com.example.demo.answer.Answer;
import com.example.demo.answer.AnswerDto;

import java.util.List;

public interface AnswerService {

    void saveAnswer(Answer answer);

    Iterable<Answer> findAll();

    List<AnswerDto> getRandom();

    List<AnswerDto> getByDifficulty();
}
