package com.example.demo.answer;

import com.example.demo.answer.Model.AnswerRepository;
import com.example.demo.answer.Model.AnswerService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    Random random = new Random();

    AnswerRepository answerRepository;

    ModelMapper modelMapper;

    UserService userService;

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        boolean answerReadyToSend;
        boolean needToBeCleared;
        int loopedNum = 0;

        List<Answer> answers = answerRepository.getAllAnsById(id);
        List<Answer> answersToSend = new ArrayList<>();

        do {
            needToBeCleared = false;
            answerReadyToSend = false;
            boolean answerWasUsed = false;
            int randomAns = random.nextInt(answers.size());

            //check if the same answer isn't being sent two times
            if (answersToSend.size() != 0) {
                for (Answer ans : answersToSend) {
                    if (ans == answers.get(randomAns)) {
                        answerWasUsed = true;
                    }
                }
            }

            //adding 1 correct, 2 incorrect answers
            if (answers.get(randomAns).isCorrect() && correctAns == 0 && !answerWasUsed) {
                answersToSend.add(answers.get(randomAns));
                correctAns++;
            } else if (!answers.get(randomAns).isCorrect() && incorrectAns < 2 && !answerWasUsed) {
                answersToSend.add(answers.get(randomAns));
                incorrectAns++;
            }

            //check if answers aren't already stored in db
            if (answersToSend.size() == 3 && user.getAnswerSet().size() != 0) {
                for (Answer ans : user.getAnswerSet()) {
                    for (Answer answerSend : answersToSend) {
                        if (answerSend == ans) {
                            needToBeCleared = true;
                        }
                    }
                }

                if(!needToBeCleared){
                    answerReadyToSend = true;
                }
                //check if aren't already saved all answers
                else if(loopedNum > 999){
                    break;
                }else {
                    loopedNum++;    //protection if all answers already saved and loop still go
                    answersToSend.clear();
                    correctAns = 0;
                    incorrectAns = 0;
                }
            }
            else if(answersToSend.size() == 3 && user.getAnswerSet().size() == 0){ //if there are no data in db jet
                answerReadyToSend = true;
            }

        } while (!answerReadyToSend );

        //add new used answers to database
        for (Answer ansToAdd : answersToSend) {
            user.getAnswerSet().add(ansToAdd);
        }

        userRepository.save(user);
        return answersToSend.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean getAnswer(long id) {
        if (answerRepository.getAnswer(id).isCorrect()) {
            return true;
        } else {
            return false;
        }
    }

    private AnswerDto convertToDto(Answer answer) {
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