package com.epam.training.ticketservice.ui.command.impl.screeningcommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class CreateScreeningCommand extends AbstractCommand {

    public CreateScreeningCommand() {
        super("create", "screening");
    }

    @Override
    public String process(String[] params) {
        return "%%% Created screening: " + params[0] + ", room: " + params[1] + ", start: " + params[2] + " " + params[3];
    }
}
