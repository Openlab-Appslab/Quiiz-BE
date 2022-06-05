package com.example.demo.favouriteQuiz;

import com.example.demo.Quiz.Quiz;
import com.example.demo.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class FavouriteQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    boolean favourite;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Quiz quiz;

}
