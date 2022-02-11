package com.example.demo.Quiz.Model;

import com.example.demo.Quiz.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
}
