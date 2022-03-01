package com.example.demo.answer.Model;

import com.example.demo.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
