package com.example.demo.Question;

import com.example.demo.Question.Model.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    private QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @PostMapping("/saveQuestions")
    public void postQuestions(@RequestBody List<Question> questions){
        questionService.saveQuestions(questions);
    }

    @GetMapping("/getQuestions")
    @ResponseBody
    public List<QuestionDto> getQuestions(){

        return questionService.findAll();
    }

    @GetMapping("/questionById")
    @ResponseBody
    public List<Question> getQuestionById(){
        return questionService.getAllByID();
    }

}
