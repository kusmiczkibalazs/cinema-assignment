package com.epam.training.ticketservice.ui.command.impl.moviecommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class UpdateMovieCommand extends AbstractCommand {

    public UpdateMovieCommand() {
        super("update", "movie");
    }

    @Override
    public String process(String[] params) {
        return "%%% Updated movie: " + params[0] + ", genre: " + params[1] + ", length: " + params[2] + " minutes";
    }
}
