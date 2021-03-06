package com.example.demo.Question;

import com.example.demo.Quiz.Quiz;
import com.example.demo.answer.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    private String chosenQuiz;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToMany(cascade =  CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<Answer> answerList = new ArrayList<>();

    public Question(String content){
        this.content = content;
    }

    public Question(String content, List<Answer> answerList){
        this.content = content;
        this.answerList = answerList;
    }
}
