package com.epam.training.ticketservice.ui.command.impl.roomcommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class CreateRoomCommand extends AbstractCommand {

    public CreateRoomCommand() {
        super("create", "room");
    }

    @Override
    public String process(String[] params) {
        return "%%% Created room: " + params[0] + ", rows: " + params[1] + ", cols: " + params[2];
    }
}
