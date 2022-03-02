package com.example.demo.answer;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import com.example.demo.answer.Model.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    private AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

    @GetMapping("/random")
    @ResponseBody
    public List<AnswerDto> getRandomAnswers(){
        return answerService.getRandom();
    }

    @GetMapping("/byDifficulty")
    @ResponseBody
    public List<AnswerDto> getAnswersByDifficulty(){
        return answerService.getByDifficulty();
    }
}
