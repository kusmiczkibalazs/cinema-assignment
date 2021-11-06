package com.epam.training.ticketservice.ui.command.impl.screeningcommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class DeleteScreeningCommand extends AbstractCommand {

    public DeleteScreeningCommand() {
        super("delete", "screening");
    }

    @Override
    public String process(String[] params) {
        return "%%% Deleted screening: " + params[0] + ", room: " + params[1] + ", start: " + params[2];
    }
}
