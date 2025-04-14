package com.popcorntime.repository;

import com.popcorntime.model.MovieCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieCategoriesRepository extends JpaRepository<MovieCategories, Integer> {
    @Query("SELECT c.category_name FROM MovieCategories c WHERE c.category_id = :categoryId")
    String getCategoryName(Integer categoryId);
}
