package com.example.demo.Question;

import com.example.demo.answer.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter @Setter @NoArgsConstructor
public class QuestionDto {

    private String content;
    private List<Answer> answerList;

    public QuestionDto(String content) {
        this.content = content;
    }
}
