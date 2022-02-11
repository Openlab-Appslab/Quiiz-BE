package com.example.demo.Quiz;

import com.example.demo.Quiz.Model.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/quiz")
    public String WriteQuiz(){
        Quiz quiz = new Quiz("dnesny den", "piatok", "pondelok", "stvrtok");
        quizService.saveQuiz(quiz);
        return quiz.getKeyAnswer();
    }
}
