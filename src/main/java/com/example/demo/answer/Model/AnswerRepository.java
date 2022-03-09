package com.example.demo.answer.Model;

import com.example.demo.Question.Question;
import com.example.demo.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("SELECT a FROM Answer a WHERE a.question.id = :id")
    List<Answer> getAllAnsById(@Param("id") long id);
}
