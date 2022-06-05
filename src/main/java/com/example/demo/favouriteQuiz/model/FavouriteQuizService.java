package com.example.demo.favouriteQuiz.model;

import com.example.demo.favouriteQuiz.FavouriteQuiz;

public interface FavouriteQuizService {
    void favoriteTrue(String quizId);

    void favoriteFalse(String quizId);

    FavouriteQuiz checkFavourite(String quizId);
}
