package com.popcorntime.mapper;

import com.popcorntime.dto.MovieCategoriesDto;
import com.popcorntime.model.MovieCategories;
import org.springframework.stereotype.Component;

@Component
public class MovieCategoriesMapper {

    public MovieCategoriesDto mapToMovieCategoriesDto(MovieCategories movieCategories) {
        return new MovieCategoriesDto(
                movieCategories.getCategory_id(),
                movieCategories.getCategory_name()
        );
    }

    public MovieCategories mapToMovieCategories(MovieCategoriesDto movieCategoriesDto) {
        return new MovieCategories(
                movieCategoriesDto.getCategory_id(),
                movieCategoriesDto.getCategory_name()
        );
    }
}

