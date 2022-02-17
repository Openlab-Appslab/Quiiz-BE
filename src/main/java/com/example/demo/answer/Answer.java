package com.example.demo.answer;

import com.example.demo.Question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    private Question question;

    private String correctAnswer;
    private String incorrectAnswer;

    public Answer(String correctAnswer, String incorrectAnswer){
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
    }
}
