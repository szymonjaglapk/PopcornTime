package com.popcorntime.mapper;

import org.springframework.stereotype.Component;

import java.util.HashSet;

import com.popcorntime.dto.AddMovieRequest;
import com.popcorntime.dto.MovieDto;
import com.popcorntime.model.Movie;

@Component
public class MovieMapper {

    public MovieDto mapToMovieDto(Movie movie) {
        return new MovieDto(
                movie.getMovie_id(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getPhoto(),
                movie.getDescription(),
                movie.getRelease_year(),
                movie.getRating()
        );
    }

    public Movie toMovie(MovieDto movieDto) {
        return new Movie(
                movieDto.getMovie_id(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getPhoto(),
                movieDto.getDescription(),
                movieDto.getRelease_year(),
                movieDto.getRating(),
                new HashSet<>()
        );
    }

    public Movie toMovie(AddMovieRequest movieRequest) {
        return new Movie(
                null,
                movieRequest.getTitle(),
                movieRequest.getDirector(),
                movieRequest.getPhoto(),
                movieRequest.getDescription(),
                movieRequest.getRelease_year(),
                0,
                new HashSet<>()
        );
    }
}
