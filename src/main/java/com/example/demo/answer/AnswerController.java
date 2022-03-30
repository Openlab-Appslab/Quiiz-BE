package com.example.demo.answer;

import com.example.demo.Question.QuestionDto;
import com.example.demo.answer.Model.AnswerService;
import com.example.demo.user.UserAnswerDto;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    private final UserService userService;

    @Autowired
    private AnswerController(AnswerService answerService, UserService userService){
        this.answerService = answerService;
        this.userService = userService;
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

    @GetMapping("/{answerId}")
    @ResponseBody
    public boolean getAnswerId(@PathVariable long answerId){
        return answerService.getAnswer(answerId);
    }

    @GetMapping("/sentAnswer")
    public UserAnswerDto getSentAnswer(){
        return userService.saveUserAnswer();
    }
}
