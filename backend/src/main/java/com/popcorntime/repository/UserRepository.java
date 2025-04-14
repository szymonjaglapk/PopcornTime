package com.popcorntime.repository;

import com.popcorntime.model.Movie;
import com.popcorntime.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u.userId FROM User u WHERE u.email = :email")
    Integer findUserIdByEmail(@Param("email") String email);

    @Query("SELECT COUNT(b) > 0 FROM User u JOIN u.watchlist b WHERE u.userId = :userId AND b.movie_id = :movieId")
    boolean userToWatch(Integer userId, Integer movieId);

    User getUserByEmail(String email);

    @Query("SELECT b FROM User u JOIN u.watchlist b WHERE u.userId = :userId")
    List<Movie> getUserwatchlist(Integer userId);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM User u JOIN u.types t WHERE u.userId = :userId AND t.name = 'TYPE_ADMIN'")
    Boolean isAdmin(Integer userId);
}
