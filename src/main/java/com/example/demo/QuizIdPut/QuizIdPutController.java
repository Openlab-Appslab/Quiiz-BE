package com.example.demo.QuizIdPut;

import com.example.demo.Quiz.Model.QuizService;
import com.example.demo.QuizIdPut.Model.QuizIdPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class QuizIdPutController {

    QuizIdPutService quizIdPutService;

    @Autowired
    public QuizIdPutController(QuizIdPutService quizIdPutService) {
        this.quizIdPutService = quizIdPutService;
    }

    @PutMapping("/questionBy/{quizId}")
    void replaceQuestionByQuizId(@PathVariable String quizId){
        quizIdPutService.replaceQuizId(quizId);
    }

    @PostMapping("/saveQuizIdToPut")
    void saveQuizIdToPut(@RequestBody QuizIdPut quizIdPut){
        quizIdPutService.saveQuizId(quizIdPut);
    }
}
