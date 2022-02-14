package com.example.demo.Quiz;

import com.example.demo.Quiz.Model.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/quiz")
    public Quiz writeQuiz(){
        Quiz quiz = new Quiz("dnesny den", "piatok", "pondelok", "stvrtok");
        quizService.saveQuiz(quiz);
        return quiz;
    }
    @PostMapping("/quiz/question")
    public void postQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }
}
