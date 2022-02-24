package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Override
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> readQuiz(Quiz quiz) {

        List<Quiz> quizzes = new ArrayList<>();
        quizRepository.findAll()
        .forEach(quizzes::add);
        return  quizzes;
    }

    @Override
    public Iterable<Quiz> findAll() {
        return quizRepository.findAll();
    }

}
