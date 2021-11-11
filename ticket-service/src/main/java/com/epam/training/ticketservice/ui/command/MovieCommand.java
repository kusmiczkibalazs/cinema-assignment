package com.epam.training.ticketservice.ui.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MovieCommand {

    @ShellMethod(key = "create movie", value = "Create a new movie")
    public String createMovie(String title, String genre, int lengthInMinutes) {
        return "%%% Created movie: " + title + ", genre: " + genre + ", length: " + lengthInMinutes + " minutes";
    }

    @ShellMethod(key = "update movie", value = "Update a movie")
    public String updateMovie(String title, String genre, int lengthInMinutes) {
        return "%%% Updated movie: " + title + ", genre: " + genre + ", length: " + lengthInMinutes + " minutes";
    }

    @ShellMethod(key = "delete movie", value = "Delete a movie")
    public String deleteMovie(String title) {
        return "%%% Deleted movie: " + title;
    }

    @ShellMethod(key = "list movies", value = "List the movies")
    public String listMovies() {
        return "%%% An imaginary list of movies";
    }
}
