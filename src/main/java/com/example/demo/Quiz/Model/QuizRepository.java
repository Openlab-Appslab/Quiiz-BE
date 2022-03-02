package com.example.demo.Quiz.Model;

import com.example.demo.Quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    @Query("SELECT q.name FROM Quiz q")
    List<String> getAllQuizNames();
}
