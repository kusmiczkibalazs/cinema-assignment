package com.epam.training.ticketservice.ui.command.impl.roomcommands;

import com.epam.training.ticketservice.ui.command.impl.AbstractCommand;

public class ListRoomsCommand extends AbstractCommand {

    public ListRoomsCommand() {
        super("list", "rooms");
    }

    @Override
    public String process(String[] params) {
        return "%%% An imaginary list of rooms";
    }
}
