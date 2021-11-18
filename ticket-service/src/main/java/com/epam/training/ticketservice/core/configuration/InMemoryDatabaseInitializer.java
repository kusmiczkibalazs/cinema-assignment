package com.epam.training.ticketservice.core.configuration;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InMemoryDatabaseInitializer {

    private final MovieRepository movieRepository;


    public InMemoryDatabaseInitializer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() {
        Movie interstellar = new Movie("Interstellar", "sci-fi", 169);
        Movie theGentleMen = new Movie("The Gentlemen", "crime", 113);
        Movie goodWillHunting = new Movie("Good Will Hunting", "drama", 126);

        movieRepository.saveAll(List.of(interstellar, theGentleMen, goodWillHunting));
    }
}
