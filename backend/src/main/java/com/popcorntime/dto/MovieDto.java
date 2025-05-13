package com.popcorntime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private int movie_id;
    private String title;
    private String director;
    private String photo;
    private String description;
    private String release_year;
    private int rating;
}