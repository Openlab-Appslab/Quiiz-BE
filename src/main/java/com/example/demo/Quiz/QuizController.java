package com.example.demo.Quiz;

import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes/get")
public class QuizController {

    //Random rand = new Random();

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuizRepository quizRepository;


    @PostMapping("/quiz/question")
    public void postQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }

    @GetMapping
    public Iterable<Quiz> getQuizzes(){
        return quizRepository.findAll();
    }

}
