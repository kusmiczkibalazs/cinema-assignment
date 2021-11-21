package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
    public void createScreening(String title, String roomName, String screeningStartDateString) {
        LocalDateTime screeningStartDate = LocalDateTime.parse(screeningStartDateString, formatter);

        Optional<MovieDto> movieDto = movieService.getMovieByTitle(title);
        Optional<RoomDto> roomDto = roomService.getRoomByRoomName(roomName);

        if (movieDto.isEmpty() && roomDto.isEmpty()) {
            throw new NullPointerException("Movie and room do not exist");
        } else if (movieDto.isEmpty()) {
            throw new NullPointerException("Movie does not exist");
        } else if (roomDto.isEmpty()) {
            throw new NullPointerException("Room does not exist");
        }

        ScreeningDto screeningDto = ScreeningDto.builder()
                        .movieDto(movieDto.get())
                        .roomDto(roomDto.get())
                        .screeningStartDate(screeningStartDate)
                        .build();
        screeningService.createScreening(screeningDto);
    }

    @ShellMethod(key = "delete screening", value = "Delete a screening")
    public void deleteScreening(String title, String roomName, String screeningStartDateString) {
        LocalDateTime screeningStartDate = LocalDateTime.parse(screeningStartDateString, formatter);

        screeningService.deleteScreening(title, roomName, screeningStartDate);
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
