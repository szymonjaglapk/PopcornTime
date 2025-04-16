package com.popcorntime.controller;

import com.popcorntime.dto.MovieCategoriesDto;
import com.popcorntime.dto.MovieDto;
import com.popcorntime.mapper.MovieCategoriesMapper;
import com.popcorntime.mapper.MovieMapper;
import com.popcorntime.service.MovieCategoriesService;
import com.popcorntime.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class MovieCategoriesController {

    private final MovieCategoriesMapper movieCategoriesMapper;
    private final MovieCategoriesService movieCategoriesService;
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping("/all")
    public ResponseEntity<List<MovieCategoriesDto>> getAllCategories() {
        return ResponseEntity.ok(
                movieCategoriesService.getAllCategories()
                        .stream()
                        .map(movieCategoriesMapper::mapToMovieCategoriesDto)
                        .toList()
        );
    }

    @GetMapping("/categoryMovies/{categoryId}")
    public ResponseEntity<List<MovieDto>> getMoviesByCategoryId(@PathVariable int categoryId) {
        return ResponseEntity.ok(
                movieService.getMoviesByCategoryId(categoryId)
                        .stream()
                        .map(movieMapper::mapToMovieDto)
                        .toList()
        );
    }

    @GetMapping("name/{categoryId}")
    public ResponseEntity<String> getCategoryName(@PathVariable int categoryId) {
        return ResponseEntity.ok(
                movieCategoriesService.getCategoryName(categoryId)
        );
    }
}
