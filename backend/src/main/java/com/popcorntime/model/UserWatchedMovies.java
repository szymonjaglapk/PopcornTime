package com.popcorntime.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_watched_movies")
public class UserWatchedMovies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uwm_id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "movie_id")
    @ManyToOne
    private Movie movie;

    @Column(name = "watched_at")
    private LocalDateTime watchedDate;

    @Column(name = "liked")
    private boolean liked;
}
