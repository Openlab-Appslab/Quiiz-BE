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
@CrossOrigin("http://localhost:4200")
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

    @GetMapping("/difficulty/{questionId}/{difficulty}")
    @ResponseBody
    public List<AnswerDto> getAnswersByDifficulty(@PathVariable long questionId, @PathVariable int difficulty){
        return answerService.getByDifficulty(questionId, difficulty);
    }
}
