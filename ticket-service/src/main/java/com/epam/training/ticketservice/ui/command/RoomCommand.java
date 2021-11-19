package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class RoomCommand {

    private final RoomService roomService;

    public RoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @ShellMethod(key = "create room", value = "Create a new room")
    public RoomDto createRoom(String roomName, int seatRowsCount, int seatColumnsCount) {
        RoomDto roomDto = RoomDto.builder()
                .roomName(roomName)
                .seatRowsCount(seatRowsCount)
                .seatColumnsCount(seatColumnsCount)
                .build();
        roomService.createRoom(roomDto);
        return roomDto;
    }

    @ShellMethod(key = "update room", value = "Update a room")
    public void updateRoom(String roomName, int seatRowsCount, int seatColumnsCount) {
        RoomDto roomDto = RoomDto.builder()
                .roomName(roomName)
                .seatRowsCount(seatRowsCount)
                .seatColumnsCount(seatColumnsCount)
                .build();
        roomService.updateRoom(roomDto);
        System.out.println("Updated room: " + roomName);
    }

    @ShellMethod(key = "delete room", value = "Delete a room")
    public void deleteRoom(String roomName) {
        roomService.deleteRoom(roomName);
        System.out.println("Deleted room: " + roomName);
    }

    @ShellMethod(key = "list rooms", value = "List the rooms")
    public void listRooms() {
        List<RoomDto> roomList = roomService.getRoomList();
        if(roomList.isEmpty()){
            System.out.println("There are no rooms at the moment");
        } else {
            roomList.forEach(System.out::println);
        }
    }
}
