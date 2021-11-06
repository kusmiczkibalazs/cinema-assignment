package com.epam.training.ticketservice.ui.command.impl.roomcommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class DeleteRoomCommand extends AbstractCommand {

    public DeleteRoomCommand() {
        super("delete", "room");
    }

    @Override
    public String process(String[] params) {
        return "%%% Deleted room: " + params[0];
    }
}
