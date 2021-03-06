package com.example.demo.Quiz.Model;

import com.example.demo.Question.Question;
import com.example.demo.Quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, String> {

    @Query("SELECT q.name FROM Quiz q")
    List<String> getAllQuizNames();

    @Query("SELECT q.name FROM Quiz q WHERE q.name = :id")
    String getQuizName(@Param("id") String id);
}
