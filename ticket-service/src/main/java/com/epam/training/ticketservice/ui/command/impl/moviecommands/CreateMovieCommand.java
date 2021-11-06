package com.epam.training.ticketservice.ui.command.impl.moviecommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class CreateMovieCommand extends AbstractCommand {

    public CreateMovieCommand() {
        super("create", "movie");
    }

    @Override
    public String process(String[] params) {
        return "%%% Created movie: " + params[0] + ", genre: " + params[1] + ", length: " + params[2] + " minutes";
    }
}
