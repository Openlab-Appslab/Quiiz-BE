package com.example.demo.answer;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import com.example.demo.answer.Model.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class AnswerContoller {

    Random random = new Random();

    @Autowired
    AnswerService answerService;

    @Autowired
    private ModelMapper modelMapper;

    private AnswerDto convertToDto(Answer answer){
        AnswerDto answerDto = modelMapper.map(answer, AnswerDto.class);
        answerDto.setContent(answer.getContent());
        return answerDto;
    }

    @GetMapping("/get/answers/random")
    @ResponseBody
    public List<AnswerDto> getAnswers(){
        int correctAns = 0;
        int incorrectAns = 0;

        List<Answer> answersToSend = new ArrayList<>();

        List<Answer> answerList = new ArrayList<>();
        Iterable<Answer> answers = answerService.findAll();
        answers.forEach(answerList::add);

        do {
            int randomAns = random.nextInt(answerList.size());
            if (answerList.get(randomAns).isCorrect() && correctAns == 0) {
                correctAns++;
                answersToSend.add(answerList.get(randomAns));
            } else if (!answerList.get(randomAns).isCorrect() && incorrectAns < 2) {
                incorrectAns++;
                answersToSend.add(answerList.get(randomAns));
            }
        }while(answersToSend.size() < 3);

        return answersToSend.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
