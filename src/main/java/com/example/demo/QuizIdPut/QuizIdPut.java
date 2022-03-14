package com.example.demo.QuizIdPut;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class QuizIdPut {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String quizIdToPut;
}
