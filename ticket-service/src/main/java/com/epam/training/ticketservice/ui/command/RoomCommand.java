package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistence.entity.User;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class RoomCommand {

    private final RoomService roomService;
    private final UserService userService;

    public RoomCommand(RoomService roomService, UserService userService) {
        this.roomService = roomService;
        this.userService = userService;
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "create room", value = "Create a new room")
    public void createRoom(String roomName, int seatRowsCount, int seatColumnsCount) {
        RoomDto roomDto = RoomDto.builder()
                .roomName(roomName)
                .seatRowsCount(seatRowsCount)
                .seatColumnsCount(seatColumnsCount)
                .build();
        roomService.createRoom(roomDto);
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "update room", value = "Update a room")
    public void updateRoom(String roomName, int seatRowsCount, int seatColumnsCount) {
        RoomDto roomDto = RoomDto.builder()
                .roomName(roomName)
                .seatRowsCount(seatRowsCount)
                .seatColumnsCount(seatColumnsCount)
                .build();
        roomService.updateRoom(roomDto);
    }

    @ShellMethodAvailability("isAdmin")
    @ShellMethod(key = "delete room", value = "Delete a room")
    public void deleteRoom(String roomName) {
        roomService.deleteRoom(roomName);
    }

    @ShellMethod(key = "list rooms", value = "List the rooms")
    public void listRooms() {
        List<RoomDto> roomList = roomService.getRoomList();
        if (roomList.isEmpty()) {
            System.out.println("There are no rooms at the moment");
        } else {
            roomList.forEach(System.out::println);
        }
    }

    private Availability isAdmin() {
        Optional<UserDto> user = userService.getLoggedInUser();
        if (user.isPresent() && user.get().getRole() == User.Role.ADMIN) {
            return Availability.available();
        }
        return Availability.unavailable("You are not an admin!");
    }
}
