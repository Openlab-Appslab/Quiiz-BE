package com.example.demo.user;

import com.example.demo.answer.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter @NoArgsConstructor
public class UserAnswerDto {
    Set<Answer> answerSet;
    long userId;
}
