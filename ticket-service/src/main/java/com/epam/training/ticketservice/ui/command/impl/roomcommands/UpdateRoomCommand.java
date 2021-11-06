package com.epam.training.ticketservice.ui.command.impl.roomcommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class UpdateRoomCommand extends AbstractCommand {

    public UpdateRoomCommand() {
        super("update", "room");
    }

    @Override
    public String process(String[] params) {
        return "%%% Updated room: " + params[0] + ", rows: " + params[1] + ", cols: " + params[2];
    }
}
