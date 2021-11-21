package com.epam.training.ticketservice.core.configuration;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import com.epam.training.ticketservice.core.user.persistence.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InMemoryDatabaseInitializer {

    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public InMemoryDatabaseInitializer(MovieRepository movieRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        Movie interstellar = new Movie("Interstellar", "sci-fi", 169);
        Movie theGentleMen = new Movie("The Gentlemen", "crime", 113);
        Movie goodWillHunting = new Movie("Good Will Hunting", "drama", 126);
        movieRepository.saveAll(List.of(interstellar, theGentleMen, goodWillHunting));

        Room pedersoli = new Room("Pedersoli", 10, 10);
        Room lumiere = new Room("Lumiere", 5,7);
        roomRepository.saveAll(List.of(pedersoli, lumiere));

        User admin = new User("admin", "admin", User.Role.ADMIN);
        userRepository.save(admin);
    }
}
