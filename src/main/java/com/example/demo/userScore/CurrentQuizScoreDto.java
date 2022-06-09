package com.example.demo.userScore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CurrentQuizScoreDto {

    int currentScore;
    String quizName;
    String userName;
}
