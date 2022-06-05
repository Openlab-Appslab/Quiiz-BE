package com.example.demo.favouriteQuiz;

import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.favouriteQuiz.model.FavouriteQuizRepository;
import com.example.demo.favouriteQuiz.model.FavouriteQuizService;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteQuizServiceImpl implements FavouriteQuizService {

    FavouriteQuizRepository favouriteQuizRepository;

    UserService userService;

    ModelMapper modelMapper;

    QuizRepository quizRepository;

    @Autowired
    public FavouriteQuizServiceImpl(FavouriteQuizRepository favouriteQuizRepository, UserService userService, ModelMapper modelMapper, QuizRepository quizRepository) {
        this.favouriteQuizRepository = favouriteQuizRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.quizRepository = quizRepository;
    }

    @Override
    public void favoriteTrue(String quizId) {
        boolean quizIsAlreadySaved = false;

        List<FavouriteQuiz> favouriteQuizList = favouriteQuizRepository.findFavouriteQuiz(quizId);
        for(FavouriteQuiz favouriteQuiz1 : favouriteQuizList){
            if(favouriteQuiz1.getUser() == getCurrentUser()){
                favouriteQuiz1.setFavourite(true);
                favouriteQuizRepository.save(favouriteQuiz1);
                quizIsAlreadySaved = true;
                break;
            }
        }

        if(!quizIsAlreadySaved){
            FavouriteQuiz favouriteQuiz = new FavouriteQuiz();
            favouriteQuiz.setQuiz(quizRepository.getQuiz(quizId));
            favouriteQuiz.setUser(getCurrentUser());
            favouriteQuiz.setFavourite(true);
            favouriteQuizRepository.save(favouriteQuiz);
        }
    }

    @Override
    public void favoriteFalse(String quizId) {
        boolean quizIsAlreadySaved = false;

            List<FavouriteQuiz> favouriteQuizList = favouriteQuizRepository.findFavouriteQuiz(quizId);
            for(FavouriteQuiz favouriteQuiz1 : favouriteQuizList){
                if(favouriteQuiz1.getUser() == getCurrentUser()){
                    favouriteQuiz1.setFavourite(false);
                    favouriteQuizRepository.save(favouriteQuiz1);
                    quizIsAlreadySaved = true;
                    break;
                }
            }

        if(!quizIsAlreadySaved){
            FavouriteQuiz favouriteQuiz = new FavouriteQuiz();
            favouriteQuiz.setQuiz(quizRepository.getQuiz(quizId));
            favouriteQuiz.setUser(getCurrentUser());
            favouriteQuiz.setFavourite(false);
            favouriteQuizRepository.save(favouriteQuiz);
        }
    }

    @Override
    public FavouriteQuiz checkFavourite(String quizId) {
        FavouriteQuiz favouriteQuiz2 = new FavouriteQuiz();
        List<FavouriteQuiz> favouriteQuiz = favouriteQuizRepository.findFavouriteQuiz(quizId);
        for(FavouriteQuiz favouriteQuiz1 : favouriteQuiz){
            if(favouriteQuiz1.getUser() == getCurrentUser()){
                favouriteQuiz1.setFavourite(false);
                favouriteQuiz2 = favouriteQuiz1;
            }
        }
        return favouriteQuiz2;
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
