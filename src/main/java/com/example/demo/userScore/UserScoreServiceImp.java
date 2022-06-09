package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserScoreServiceImp implements UserScoreService {

    ModelMapper modelMapper;

    UserScoreRepository userScoreRepository;

    UserService userService;

    @Autowired
    public UserScoreServiceImp(ModelMapper modelMapper, UserScoreRepository userScoreRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.userScoreRepository = userScoreRepository;
        this.userService = userService;
    }

    @Override
    public void saveScore(Quiz quiz, Integer score) {
        boolean userScoreIsSaved = false;
        User user = this.getCurrentUser();

        List< UserScore > userScores = userScoreRepository.findFavouriteQuiz(quiz.getName());
        for(UserScore userScore : userScores){
            if(userScore.getUser() == getCurrentUser()){
                userScore.setScore(score + userScore.getScore());
                convertToDto(userScoreRepository.save(userScore));
                userScoreIsSaved = true;
                break;
            }
        }
        if(!userScoreIsSaved) {
            UserScore userScore = new UserScore();
            userScore.setUser(user);
            userScore.setQuiz(quiz);
            userScore.setScore(score);
            convertToDto(userScoreRepository.save(userScore));
        }
    }

    @Override
    public Integer getAllScore() {
        User user = getCurrentUser();

        return userScoreRepository.getScoreForUser(user.getId()).stream().reduce(0, Integer::sum);
    }

    @Override
    public Integer getAllScoreByQuiz(String quizId) {
        int score = 0;
        List<UserScore> userScoreList = userScoreRepository.getScoreForUserByQuiz(quizId);
        User user = getCurrentUser();

        for(UserScore userScore : userScoreList){
            if(user.getId() == userScore.getUser().getId()){
                score += userScore.getScore();
            }
        }

        return score;
    }

    @Override
    public List<UserScoreDto> getAllScoreForAllQuiz() {
        List<UserScore> userScores = userScoreRepository.getAllUserScore();
        return userScores.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<AllScoreDto> getAllScoreForAllUser() {
        List<AllScoreDto> allScoreDtos = new ArrayList<>();
        Set<User> users = userScoreRepository.getAllUsers();

        for(User user : users){
            AllScoreDto allScoreDto = new AllScoreDto();
            allScoreDto.score = userScoreRepository.getScoreForUser(user.getId()).stream().reduce(0, Integer::sum);
            allScoreDto.userName = user.getUsername();
            allScoreDtos.add(allScoreDto);
        }
        return allScoreDtos;
    }

    @Override
    public List<UserScoreDto> getScoreForUser() {
        return userScoreRepository.getUserScoreByUserId(getCurrentUser().getId()).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Integer getScoreForCurrentQuiz(String quizId, Integer score) {
        int newScore = 0;
        List<UserScore> userScores = userScoreRepository.getScoreForUserByQuiz(quizId);

        for(UserScore userScore : userScores){
            if(userScore.getUser().getId() == getCurrentUser().getId()){
                userScore.setCurrentScore(userScore.getCurrentScore() + score);
                newScore = userScore.getCurrentScore();
                userScoreRepository.save(userScore);
            }
        }
        return newScore;
    }

    @Override
    public void resetCurrentScore(String quizId) {
        List<UserScore> userScores = userScoreRepository.getScoreForUserByQuiz(quizId);

        for(UserScore userScore : userScores){
            if(userScore.getUser().getId() == getCurrentUser().getId()){
                userScore.setCurrentScore(0);
                userScoreRepository.save(userScore);
            }
        }

    }

    @Override
    public Integer getCurrentScore(String quizId) {
        int currentScore = 0;
        List<UserScore> userScores = userScoreRepository.getScoreForUserByQuiz(quizId);

        for(UserScore userScore : userScores){
            if(userScore.getUser().getId() == getCurrentUser().getId()){
                currentScore = userScore.getCurrentScore();
            }
        }
        return currentScore;
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return this.userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    private UserScoreDto convertToDto(UserScore userScore){
        UserScoreDto userScoreDto = modelMapper.map(userScore, UserScoreDto.class);
        userScoreDto.setUserName(userScore.getUser().getUsername());
        userScoreDto.setScore(userScore.getScore());
        userScoreDto.setQuizName(userScore.getQuiz().getName());
        return userScoreDto;
    }

    private CurrentQuizScoreDto convertToAllScoreDto(UserScore userScore){
        CurrentQuizScoreDto userScoreDto = modelMapper.map(userScore, CurrentQuizScoreDto.class);
        userScoreDto.setUserName(userScore.getUser().getUsername());
        userScoreDto.setCurrentScore(userScore.getCurrentScore());
        return userScoreDto;
    }
}
