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
public class AnswerContoller {

    Random random = new Random();

    private final AnswerService answerService;

    private final ModelMapper modelMapper;

    @Autowired
    private AnswerContoller(AnswerService answerService, ModelMapper modelMapper){
        this.answerService = answerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/random")
    @ResponseBody
    public List<AnswerDto> getRandomAnswers(){
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
    int chooseAns = 0;
    @GetMapping("/byDifficulty")
    @ResponseBody
    public List<AnswerDto> getAnswersByDifficulty(){
        int correctAns = 0;
        int incorrectAns = 0;


        List<Answer> answersToSend = new ArrayList<>();

        List<Answer> answerList = new ArrayList<>();
        Iterable<Answer> answers = answerService.findAll();
        answers.forEach(answerList::add);

        do {
            if (answerList.get(chooseAns).isCorrect() && correctAns == 0) {
                correctAns++;
                answersToSend.add(answerList.get(chooseAns));
            } else if (!answerList.get(chooseAns).isCorrect() && incorrectAns < 2) {
                incorrectAns++;
                answersToSend.add(answerList.get(chooseAns));
            }
            chooseAns++;
            if(chooseAns == answerList.size()){
                chooseAns = 0;
            }
        }while(answersToSend.size() < 3);

        for(Answer answer : answersToSend){
            answer.setSent(true);
        }
        return answersToSend.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    private AnswerDto convertToDto(Answer answer){
        AnswerDto answerDto = modelMapper.map(answer, AnswerDto.class);
        answerDto.setContent(answer.getContent());
        answerDto.setSent(answer.isSent());
        return answerDto;
    }
}