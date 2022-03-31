package com.example.demo.answer;

import com.example.demo.answer.Model.AnswerRepository;
import com.example.demo.answer.Model.AnswerService;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    UserService userService;

    @Autowired
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<AnswerDto> getRandom(long id) {
        User user = this.getCurrentUser();
        int correctAns = 0;
        int incorrectAns = 0;
        boolean answerWasUsed = false;

        List<Answer> answers = answerRepository.getAllAnsById(id);
        List<Answer> answersToSend = new ArrayList<>();

        do {
            int randomAns = random.nextInt(answers.size());
            if (answers.get(randomAns).isCorrect() && correctAns == 0) {
                //setting sent ans for user
                for(Answer answer : user.getAnswerSet() ){
                    if(answer == answers.get(randomAns)){
                        answerWasUsed = true;
                    }
                }
                if(!answerWasUsed){
                    answersToSend.add(answers.get(randomAns));
                    correctAns++;
                }
                //

                answerWasUsed = false;
            } else if (!answers.get(randomAns).isCorrect() && incorrectAns < 2) {
                //setting sent ans for user
                for(Answer answer : user.getAnswerSet() ){
                    if(answer == answers.get(randomAns)){
                        answerWasUsed = true;
                    }
                }
                if(!answerWasUsed){
                    answersToSend.add(answers.get(randomAns));
                    incorrectAns++;
                }
                //
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

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return this.userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}