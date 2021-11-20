package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ShellComponent
public class ScreeningCommand {

    private final ScreeningService screeningService;
    private final MovieService movieService;
    private final RoomService roomService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ScreeningCommand(ScreeningService screeningService, MovieService movieService, RoomService roomService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
        this.roomService = roomService;
    }

    @ShellMethod(key = "create screening", value = "Create a new screening")
    public ScreeningDto createScreening(String title, String roomName, String screeningStartDateString) {
        LocalDateTime screeningStartDate = LocalDateTime.parse(screeningStartDateString, formatter);

        ScreeningDto screeningDto = ScreeningDto.builder()
                        .movieDto(movieService.getMovieByTitle(title).get())
                        .roomDto(roomService.getRoomByRoomName(roomName).get())
                        .screeningStartDate(screeningStartDate)
                        .build();
        screeningService.createScreening(screeningDto);
        return screeningDto;

        //TODO NULLCHECK
    }

    @ShellMethod(key = "delete screening", value = "Delete a screening")
    public void deleteScreening(String title, String roomName, String screeningStartDateString) {
        LocalDateTime screeningStartDate = LocalDateTime.parse(screeningStartDateString, formatter);

        screeningService.deleteScreening(title, roomName, screeningStartDate);
        System.out.println("Deleted screening: " + title + ", screened in room " + roomName + ", at " + screeningStartDateString);
    }

    @ShellMethod(key = "list screenings", value = "List the screenings")
    public void listScreenings() {
        List<ScreeningDto> screeningList = screeningService.getScreeningList();
        if (screeningList.isEmpty()) {
            System.out.println("There are no screenings");
        } else {
            screeningList.forEach(System.out::println);
        }
    }
}
