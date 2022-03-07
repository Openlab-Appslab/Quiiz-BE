package com.example.demo.answer;

import com.example.demo.Question.Question;
import com.example.demo.Quiz.Quiz;
import com.example.demo.answer.Model.AnswerRepository;
import com.example.demo.answer.Model.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    int chooseAns = 0;

    Random random = new Random();

    AnswerRepository answerRepository;

    ModelMapper modelMapper;

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AnswerDto> getRandom() {
        int correctAns = 0;
        int incorrectAns = 0;

        List<Answer> answers = answerRepository.findAll();

        List<Answer> answersToSend = new ArrayList<>();

        do {
            int randomAns = random.nextInt(answers.size());
            if (answers.get(randomAns).isCorrect() && correctAns == 0) {
                correctAns++;
                answersToSend.add(answers.get(randomAns));
            } else if (!answers.get(randomAns).isCorrect() && incorrectAns < 2) {
                incorrectAns++;
                answersToSend.add(answers.get(randomAns));
            }
        }while(answersToSend.size() < 3);

        return answersToSend.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<AnswerDto> getByDifficulty() {
        int correctAns = 0;
        int incorrectAns = 0;

        List<Answer> answersToSend = new ArrayList<>();
        List<Answer> answers = answerRepository.findAll();

        do {
            if (answers.get(chooseAns).isCorrect() && correctAns == 0) {
                correctAns++;
                answersToSend.add(answers.get(chooseAns));
            } else if (!answers.get(chooseAns).isCorrect() && incorrectAns < 2) {
                incorrectAns++;
                answersToSend.add(answers.get(chooseAns));
            }
            chooseAns++;
            if(chooseAns == answers.size()){
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
        return answerDto;
    }
}