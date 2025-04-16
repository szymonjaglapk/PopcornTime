package com.popcorntime.controller;

import com.popcorntime.dto.UserInfoResponse;
import com.popcorntime.model.Movie;
import com.popcorntime.model.User;
import com.popcorntime.repository.MovieRepository;
import com.popcorntime.repository.UserRepository;
import com.popcorntime.service.JwtService;
import com.popcorntime.service.UserWatchlistService;
import com.popcorntime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usermovie")
@RequiredArgsConstructor
public class UserWatchlistController {
    private final UserWatchlistService userWatchlistService;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @GetMapping("/{movieId}")
    public ResponseEntity<UserInfoResponse> gerUserMovieInfo(@PathVariable int movieId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Integer userId = userService.getUserIdByEmail(email);

        return ResponseEntity.ok(
                userWatchlistService.getUserMovieInfo(userId, movieId)
        );
    }

    @PutMapping("/add/{movieId}")
    public void  addUserWatchedMovie(@PathVariable int movieId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.getUserByEmail(email);
        Integer userId = userService.getUserIdByEmail(email);
        Movie movie = movieRepository.findById(movieId).orElse(null);

        user.removeFromWatchlist(movie);
        userRepository.save(user);

        userWatchlistService.save(user, movie);
    }

    @PutMapping("/delete/{movieId}")
    public void  deleteUserWatchedMovie(@PathVariable int movieId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.getUserByEmail(email);
        Integer userId = userService.getUserIdByEmail(email);

        userWatchlistService.deleteWatchedMovie(userId, movieId);
    }

    @PutMapping("/addwatchlist/{movieId}")
    public void  addWatchlist(@PathVariable int movieId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.getUserByEmail(email);

        Movie movie = movieRepository.findById(movieId).orElse(null);

        user.addWatchlist(movie);
        userRepository.save(user);
    }

    @PutMapping("/removewatchlist/{movieId}")
    public void  removeFromWatchlist(@PathVariable int movieId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.getUserByEmail(email);

        Movie movie = movieRepository.findById(movieId).orElse(null);

        user.removeFromWatchlist(movie);
        userRepository.save(user);
    }

    @PutMapping("/like/{movieId}")
    public void  userLikeWatchedMovie(@PathVariable int movieId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Integer userId = userService.getUserIdByEmail(email);

        userWatchlistService.userLikeWatchedMovie(userId, movieId);
    }

    @PutMapping("/unlike/{movieId}")
    public void  userUnLikeWatchedMovie(@PathVariable int movieId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Integer userId = userService.getUserIdByEmail(email);

        userWatchlistService.userUnLikeWatchedMovie(userId, movieId);
    }
}

