package com.epam.training.ticketservice.ui.command.impl.moviecommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class DeleteMovieCommand extends AbstractCommand {

    public DeleteMovieCommand() {
        super("delete", "movie");
    }

    @Override
    public String process(String[] params) {
        return "%%% Deleted movie: " + params[0];
    }
}
