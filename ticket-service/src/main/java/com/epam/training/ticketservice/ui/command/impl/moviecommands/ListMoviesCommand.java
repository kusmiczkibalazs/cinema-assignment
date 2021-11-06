package com.epam.training.ticketservice.ui.command.impl.moviecommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class ListMoviesCommand extends AbstractCommand {

    public ListMoviesCommand() {
        super("list", "movies");
    }

    @Override
    public String process(String[] params) {
        return "%%% An imaginary list of movies";
    }
}
