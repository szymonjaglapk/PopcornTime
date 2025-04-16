package com.popcorntime.service;

import com.popcorntime.exception.MovieNotFoundException;
import com.popcorntime.model.Movie;
import com.popcorntime.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> getBestRating() {
        return movieRepository.findTop3ByOrderByRatingDesc();
    }

    public List<Movie> getRandomMovies() {
        return movieRepository.findRandomThreeMovies();
    }

    public Movie getMovieById(Integer movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
    }

    public List<Movie> getMoviesByCategoryId(Integer categoryId) {
        return movieRepository.getMoviesByCategoryId(categoryId);
    }

    public List<Movie>  getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getUserWatchlist(Integer userId) {
        return movieRepository.getUserWatchedMovies(userId);
    }

    public void addNewMovie(Movie movie) {
        movieRepository.save(movie);
    }
}

