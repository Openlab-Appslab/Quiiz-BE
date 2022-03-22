package com.example.demo.answer;

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
    public List<AnswerDto> getRandom(long id) {
        int correctAns = 0;
        int incorrectAns = 0;

        List<Answer> answers = answerRepository.getAllAnsById(id);
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
    public boolean getAnswer(long id) {
        if(answerRepository.getAnswer(id).isCorrect()) {
            return true;
        }
        else{
            return false;
        }
    }

    private AnswerDto convertToDto(Answer answer){
        AnswerDto answerDto = modelMapper.map(answer, AnswerDto.class);
        answerDto.setContent(answer.getContent());
        answerDto.setId(answer.getId());
        return answerDto;
    }
}