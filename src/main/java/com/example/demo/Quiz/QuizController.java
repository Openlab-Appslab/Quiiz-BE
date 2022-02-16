package com.example.demo.Quiz;

import com.example.demo.Quiz.Model.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class QuizController {

    @Autowired
    QuizService quizService;
    Quiz quiz;
    @GetMapping("/quiz/add")
    public Quiz writeQuiz(){
        Quiz createdQuiz = new Quiz("dnesny den", "piatok", "pondelok", "stvrtok");
        quizService.saveQuiz(createdQuiz);
        return createdQuiz;
    }
    @PostMapping("/quiz/question")
    public void postQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }

    @RequestMapping("/quiz/get")
    public List<Quiz> getQuiz(){
        List<Quiz> quizList;
        quizList = quizService.readQuiz(quiz);
        return quizList;
    }
}
