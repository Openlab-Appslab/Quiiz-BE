package com.example.demo.answer.Model;

import com.example.demo.Question.QuestionDto;
import com.example.demo.answer.AnswerDto;

import java.util.List;

public interface AnswerService {

    List<AnswerDto> getRandom();

    List<AnswerDto> getByDifficulty();

    //List<AnswerDto> getAllByID();
}
