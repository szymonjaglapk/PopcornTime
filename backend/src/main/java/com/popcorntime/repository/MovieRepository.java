package com.popcorntime.repository;

import com.popcorntime.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findTop3ByOrderByRatingDesc();

    @Query("SELECT b from Movie b ORDER BY RANDOM() LIMIT 3")
    List<Movie> findRandomThreeMovies();

    @Query("SELECT b FROM Movie b JOIN b.categories c WHERE c.category_id = :categoryId")
    List<Movie> getMoviesByCategoryId(Integer categoryId);

    List<Movie> findAll();

    @Query("SELECT uwm.movie FROM UserWatchedMovies uwm WHERE uwm.user.userId = :userId")
    List<Movie> getUserWatchedMovies (Integer userId);
}
