package com.popcorntime.service;

import com.popcorntime.model.MovieCategories;
import com.popcorntime.repository.MovieCategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class MovieCategoriesService {
    private final MovieCategoriesRepository movieCategoriesRepository;

    public List<MovieCategories> getAllCategories() {

        return movieCategoriesRepository.findAll();
    }

    public String getCategoryName(Integer categoryId) {
        return movieCategoriesRepository.getCategoryName(categoryId);
    }
}
