package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class MovieCommand {

    private final MovieService movieService;

    public MovieCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @ShellMethod(key = "create movie", value = "Create a new movie")
    public MovieDto createMovie(String title, String genre, int lengthInMinutes) {
        MovieDto movieDto = MovieDto.builder()
                .title(title)
                .genre(genre)
                .lengthInMinutes(lengthInMinutes)
                .build();
        movieService.createMovie(movieDto);
        return movieDto;
    }

    @ShellMethod(key = "update movie", value = "Update a movie")
    public String updateMovie(String title, String genre, int lengthInMinutes) {
        //TODO
        return "%%% Updated movie: " + title + ", genre: " + genre + ", length: " + lengthInMinutes + " minutes";
    }

    @ShellMethod(key = "delete movie", value = "Delete a movie")
    public void deleteMovie(String title) {
        movieService.deleteMovie(title);
    }

    @ShellMethod(key = "list movies", value = "List the movies")
    public List<MovieDto> listMovies() {
        return movieService.getMovieList();
    }
}