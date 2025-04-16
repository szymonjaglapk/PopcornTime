package com.popcorntime.controller;

import com.popcorntime.dto.AddMovieRequest;
import com.popcorntime.dto.MovieDto;
import com.popcorntime.mapper.MovieMapper;
import com.popcorntime.service.MovieService;
import com.popcorntime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final UserService userService;

    @GetMapping("/movieinfo/{movieId}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable int movieId) {
        return ResponseEntity.ok(
                movieMapper.mapToMovieDto(movieService.getMovieById(movieId))
        );
    }

    @GetMapping("/bestrating")
    public ResponseEntity<List<MovieDto>> getBestRating() {
        return ResponseEntity.ok(
                movieService.getBestRating()
                        .stream()
                        .map(movieMapper::mapToMovieDto)
                        .toList()
        );
    }

    @GetMapping("/random")
    public ResponseEntity<List<MovieDto>> getRandomMovies() {
        return ResponseEntity.ok(
                movieService.getRandomMovies()
                        .stream()
                        .map(movieMapper::mapToMovieDto)
                        .toList()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(
                movieService.getAllMovies()
                        .stream()
                        .map(movieMapper::mapToMovieDto)
                        .toList()
        );
    }

    @GetMapping("/userwatchlist")
    public ResponseEntity<List<MovieDto>> getUserWatchlist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Integer userId = userService.getUserIdByEmail(email);

        return ResponseEntity.ok(
                movieService.getUserWatchlist(userId)
                        .stream()
                        .map(movieMapper::mapToMovieDto)
                        .toList()
        );
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestBody AddMovieRequest movie) {
        movieService.addNewMovie(movieMapper.toMovie(movie));
    }
}
