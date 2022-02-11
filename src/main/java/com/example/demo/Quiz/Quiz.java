package com.example.demo.Quiz;

import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @NoArgsConstructor
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String question;
    private String keyAnswer;
    private String answer1;
    private String answer2;

    public Quiz(String question, String keyAnswer, String answer1, String answer2){
        this.question = question;
        this.keyAnswer = keyAnswer;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }
}
