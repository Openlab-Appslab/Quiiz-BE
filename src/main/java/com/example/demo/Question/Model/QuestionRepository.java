package com.example.demo.Question.Model;

import com.example.demo.Question.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("questionRepository")
public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
