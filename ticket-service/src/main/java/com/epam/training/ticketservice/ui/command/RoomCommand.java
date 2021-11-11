package com.epam.training.ticketservice.ui.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class RoomCommand {

    @ShellMethod(key = "create room", value = "Create a new room")
    public String createRoom(String roomName, int seatRowsCount, int seatColumnsCount) {
        return "%%% Created room: " + roomName + ", rows: " + seatRowsCount + ", cols: " + seatColumnsCount;
    }

    @ShellMethod(key = "update room", value = "Update a room")
    public String updateRoom(String roomName, int seatRowsCount, int seatColumnsCount) {
        return "%%% Updated room: " + roomName + ", rows: " + seatRowsCount + ", cols: " + seatColumnsCount;
    }

    @ShellMethod(key = "delete room", value = "Delete a room")
    public String deleteRoom(String roomName) {
        return "%%% Deleted room: " + roomName;
    }

    @ShellMethod(key = "list rooms", value = "List the rooms")
    public String listRooms() {
        return "%%% An imaginary list of rooms";
    }
}
