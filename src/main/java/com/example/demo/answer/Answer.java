package com.example.demo.answer;

import com.example.demo.Question.Question;
import com.example.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Question.class)
    @JoinColumn(name = "question_id")
    private Question question;

    private int difficulty;
//    @ManyToMany(mappedBy = "answerSet")
//    Set<User> user;

    private String content;
    private boolean correct;

    public Answer(String content, boolean correct){
        this.content = content;
        this.correct = correct;
    }
}
