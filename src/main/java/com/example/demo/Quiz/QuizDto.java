package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class QuizDto {

    private String name;

    private List<QuestionDto> questionList;

    private boolean favourite;

    private String description;
}
