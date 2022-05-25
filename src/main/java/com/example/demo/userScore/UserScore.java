package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    int score;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Quiz quiz;
}