package com.example.demo.answer;

import com.example.demo.Question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    private String content;
    private boolean correct;

    public Answer(String content, boolean correct){
        this.content = content;
        this.correct = correct;
    }
}
