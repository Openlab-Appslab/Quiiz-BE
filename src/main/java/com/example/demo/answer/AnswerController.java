package com.example.demo.answer;

import com.example.demo.answer.Model.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    private AnswerController(AnswerService answerService){
        this.answerService = answerService;
    }

//    @GetMapping("/random")
//    @ResponseBody
//    public List<AnswerDto> getRandomAnswers(){
//        return answerService.getRandom();
//    }

//    @GetMapping("/byId")
//    @ResponseBody
//    public List<AnswerDto> getAnswersById(){
//        return answerService.getAllByID();
//    }
}
