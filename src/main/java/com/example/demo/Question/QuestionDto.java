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

    //Random rand = new Random();

    private String content;

    private List<Answer> answerList;
    //List<Answer> sendingAnswers = new ArrayList<>();


//    private List<Answer> getTreeAnswers(){
//        int randAnswer = rand.nextInt(answerList.size());
//
//        sendingAnswers.add(answerList.get(randAnswer));
//        randAnswer = rand.nextInt(answerList.size());
//        sendingAnswers.add(answerList.get(randAnswer));
//        randAnswer = rand.nextInt(answerList.size());
//        sendingAnswers.add(answerList.get(randAnswer));
//
//        return sendingAnswers;
//    }

    public QuestionDto(String content) {
        this.content = content;
    }
}
