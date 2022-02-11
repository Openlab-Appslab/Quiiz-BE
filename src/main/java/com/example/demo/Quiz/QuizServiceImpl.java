package com.example.demo.Quiz;

import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import org.springframework.stereotype.Service;

@Service
public class QuizServiceImpl implements QuizService {

    QuizRepository quizRepository;

    @Override
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }
}
