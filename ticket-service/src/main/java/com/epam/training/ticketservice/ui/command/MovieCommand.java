package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class MovieCommand {

    private final MovieService movieService;
    private final UserService userService;

    public MovieCommand(MovieService movieService, UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create movie", value = "Create a new movie")
    public void createMovie(String title, String genre, int lengthInMinutes) {
        MovieDto movieDto = MovieDto.builder()
                .title(title)
                .genre(genre)
                .lengthInMinutes(lengthInMinutes)
                .build();
        movieService.createMovie(movieDto);
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "update movie", value = "Update a movie")
    public void updateMovie(String title, String genre, int lengthInMinutes) {
        MovieDto movieDto = MovieDto.builder()
                .title(title)
                .genre(genre)
                .lengthInMinutes(lengthInMinutes)
                .build();
        movieService.updateMovie(movieDto);
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "delete movie", value = "Delete a movie")
    public void deleteMovie(String title) {
        movieService.deleteMovie(title);
    }

    @ShellMethod(key = "list movies", value = "List the movies")
    public void listMovies() {
        List<MovieDto> movieList = movieService.getMovieList();
        if (movieList.isEmpty()) {
            System.out.println("There are no movies at the moment");
        } else {
            movieList.forEach(System.out::println);
        }
    }

    private Availability isAdmin() {
        Optional<UserDto> user = userService.getLoggedInUser();
        if (user.isPresent() && user.get().getRole() == User.Role.ADMIN) {
            return Availability.available();
        }
        return Availability.unavailable("You are not an admin!");
    }
}
