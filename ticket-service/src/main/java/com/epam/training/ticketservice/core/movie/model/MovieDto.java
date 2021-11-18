package com.epam.training.ticketservice.core.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class MovieDto {

    private final String title;
    private String genre;
    private int lengthInMinutes;

    @Override
    public String toString() {
        return title + " (" + genre + ", " + lengthInMinutes + " minutes)";
    }
}
