package com.example.demo.answer.Model;

import com.example.demo.answer.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
}
