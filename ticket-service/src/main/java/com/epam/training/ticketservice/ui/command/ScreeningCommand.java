package com.epam.training.ticketservice.ui.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ScreeningCommand {

    @ShellMethod(key = "create screening", value = "Create a new screening")
    public String createScreening(String title, String room, String screeningStartDate, String screeningStartTime) {
        return "%%% Created screening: " + title + ", room: " + room + ", start: " + screeningStartDate + " " + screeningStartTime;
    }

    @ShellMethod(key = "delete screening", value = "Delete a screening")
    public String deleteScreening(String title, String room, String screeningStartDate, String screeningStartTime) {
        return "%%% Deleted screening: " + title + ", room: " + room + ", start: " + screeningStartDate + " " + screeningStartTime;
    }

    @ShellMethod(key = "list screenings", value = "List the screenings")
    public String listScreenings() {
        return "%%% An imaginary list of screenings";
    }
}
