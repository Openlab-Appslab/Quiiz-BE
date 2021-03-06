package com.example.demo.Question;

import com.example.demo.answer.AnswerDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class QuestionDto {

    private long id;
    private String content;
    private List<AnswerDto> answerList;

    public QuestionDto(String content) {
        this.content = content;
    }
}
