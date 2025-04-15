package com.popcorntime.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMovieRequest {

    private String title;
    private String director;
    private String photo;
    private String description;
    private String release_year;

}
