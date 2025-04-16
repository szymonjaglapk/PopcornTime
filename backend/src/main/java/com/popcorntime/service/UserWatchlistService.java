package com.popcorntime.service;

import com.popcorntime.dto.UserInfoResponse;
import com.popcorntime.model.Movie;
import com.popcorntime.model.User;
import com.popcorntime.model.UserWatchedMovies;
import com.popcorntime.repository.UserWatchedMoviesRepository;
import com.popcorntime.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserWatchlistService {
    private final UserWatchedMoviesRepository userWatchedMoviesRepository;
    private final UserRepository userRepository;

    public UserInfoResponse getUserMovieInfo(Integer userId, Integer movieId) {

        boolean res1 = userWatchedMoviesRepository.existsByUserIdAndMovieId(userId, movieId);
        boolean res2 = userRepository.userToWatch(userId, movieId);
        boolean res3 = userWatchedMoviesRepository.existsByUserIdAndMovieIdAndLiked(userId, movieId);

        return UserInfoResponse.builder()
                .isWatched(res1)
                .isToWatch(res2)
                .isLiked(res3)
                .build();

    }

    public void save(User user, Movie movie) {
        LocalDateTime now = LocalDateTime.now();

        userWatchedMoviesRepository.save(
                UserWatchedMovies.builder()
                        .movie(movie)
                        .user(user)
                        .watchedDate(now)
                        .liked(false)
                        .build()
        );
    }

    public void userLikeWatchedMovie(Integer userId, Integer movieId) {
        userWatchedMoviesRepository.userLikeWatchedMovie(userId, movieId);
    }

    public void userUnLikeWatchedMovie(Integer userId, Integer movieId) {
        userWatchedMoviesRepository.userUnLikeWatchedMovie(userId, movieId);
    }

    public void deleteWatchedMovie(Integer userId, Integer movieId) {
        userWatchedMoviesRepository.deleteWatchedMovie(userId, movieId);
    }
}
