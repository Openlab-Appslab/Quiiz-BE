package com.example.demo.favouriteQuiz;

import com.example.demo.favouriteQuiz.model.FavouriteQuizService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class FavouriteQuizController {

    FavouriteQuizService favouriteQuizService;

    public FavouriteQuizController(FavouriteQuizService favouriteQuizService) {
        this.favouriteQuizService = favouriteQuizService;
    }

    @PutMapping("/setFavourite/true/{quizId}")
    @ResponseBody
    public void favoriteFalse(@PathVariable String quizId){
        favouriteQuizService.favoriteTrue(quizId);
    }
    @PutMapping("/setFavourite/false/{quizId}")
    @ResponseBody
    public void favoriteTrue(@PathVariable String quizId){
        favouriteQuizService.favoriteFalse(quizId);
    }

    @GetMapping("/favourite/{quizId}")
    @ResponseBody
    public void checkFavourite(@PathVariable String quizId){
        favouriteQuizService.checkFavourite(quizId);
    }
}
