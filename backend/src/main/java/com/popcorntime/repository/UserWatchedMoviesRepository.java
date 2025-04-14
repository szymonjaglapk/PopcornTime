package com.popcorntime.repository;
import com.popcorntime.model.UserWatchedMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserWatchedMoviesRepository extends JpaRepository<UserWatchedMovies, Integer> {

    @Query("SELECT COUNT(uwm) > 0 FROM UserWatchedMovies uwm WHERE uwm.user.userId = :userId AND uwm.movie.movie_id = :movieId")
    boolean existsByUserIdAndMovieId(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    @Query("SELECT COUNT(uwm) > 0 FROM UserWatchedMovies uwm WHERE uwm.user.userId = :userId AND uwm.movie.movie_id = :movieId AND uwm.liked = true")
    boolean existsByUserIdAndMovieIdAndLiked(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    @Modifying
    @Transactional
    @Query("UPDATE UserWatchedMovies uwm SET uwm.liked = true WHERE uwm.user.userId = :userId AND uwm.movie.movie_id = :movieId")
    void userLikeWatchedMovies(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    @Modifying
    @Transactional
    @Query("UPDATE UserWatchedMovies uwm SET uwm.liked = false WHERE uwm.user.userId = :userId AND uwm.movie.movie_id = :movieId")
    void userUnLikeWatchedMovies(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserWatchedMovies uwm WHERE uwm.user.userId = :userId AND uwm.movie.movie_id = :movieId")
    void deleteWatchedMovie(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

}
