package com.example.demo.Question.Model;

import com.example.demo.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("questionRepository")
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("SELECT q.content FROM Question q WHERE q.id = :id")
    List<Question> getAllById(@Param("id") long id);
}
