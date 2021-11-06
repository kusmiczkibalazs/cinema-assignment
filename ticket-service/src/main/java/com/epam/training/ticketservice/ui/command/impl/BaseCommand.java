package com.epam.training.ticketservice.ui.command.impl;

public class BaseCommand extends AbstractCommand {

    public BaseCommand(String entityType, String action) {
        super(entityType, action);
    }

    @Override
    public String process(String[] params) {
        throw new UnsupportedOperationException();
    }
}
