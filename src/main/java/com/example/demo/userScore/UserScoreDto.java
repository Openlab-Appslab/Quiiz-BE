package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;
import com.example.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UserScoreDto {

    int score;
    String userName;
    String quizName;
}
