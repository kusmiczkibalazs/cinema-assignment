package com.epam.training.ticketservice.ui.command.impl.screeningcommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class ListScreeningsCommand extends AbstractCommand {

    public ListScreeningsCommand() {
        super("list", "screenings");
    }

    @Override
    public String process(String[] params) {
        return "%%% An imaginary list of screenings";
    }
}
