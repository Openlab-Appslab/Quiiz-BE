package com.example.demo.Quiz;

import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    QuizRepository quizRepository;

    QuizService quizService;

    ModelMapper modelMapper;

    UserService userService;

    UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public List<QuizDto> readQuizzes() {

        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllQuizNames() {
        return quizRepository.getAllQuizNames();
    }

    @Override
    public String getQuizName(String quizName) {
        return quizRepository.getQuizName(quizName);
    }

    @Override
    public void favoriteFalse(String quizId) {
        Quiz quiz02 = quizRepository.getQuiz(quizId);
        User user = getCurrentUser();
        //user.setQuiz(quizRepository.getQuiz(quizId));
        userRepository.save(user);
    }

    @Override
    public void favoriteTrue(String quizId) {
        User user = getCurrentUser();
        Quiz quiz02 = quizRepository.getQuiz(quizId);
        quiz02.setFavourite(true);
        quizRepository.save(quiz02);
    }

    private QuizDto convertToDto(Quiz quiz){
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        quizDto.setName(quiz.getName());
        quizDto.setFavourite(quiz.isFavourite());
        quizDto.setDescription(quiz.getDescription());
        return quizDto;
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
