package com.epam.training.ticketservice.ui.command.impl;

import com.epam.training.ticketservice.ui.command.Command;

import java.util.Arrays;
import java.util.Objects;

public abstract class AbstractCommand implements Command {

    private final String entityType;
    private final String action;

    public AbstractCommand(String entityType, String action) {
        this.entityType = entityType;
        this.action = action;
    }

    @Override
    public String process(String commandString) {
        String[] commandParts = commandString.split(" ");
        return process(Arrays.copyOfRange(commandParts, 2, commandParts.length));
    }

    public abstract String process(String[] params);

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof AbstractCommand)) {
            return false;
        }
        AbstractCommand other = (AbstractCommand) obj;
        if (!action.equals(other.action)) {
            return false;
        } else {
            return entityType.equals(other.entityType);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityType, action);
    }
}
