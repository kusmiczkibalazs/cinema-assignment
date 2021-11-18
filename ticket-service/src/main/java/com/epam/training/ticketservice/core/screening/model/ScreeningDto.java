package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.model.RoomDto;

import java.util.Date;

public class ScreeningDto {

    private final MovieDto movieDto;
    private final RoomDto roomDto;
    private final Date screeningStartDate;

    public ScreeningDto(MovieDto movieDto, RoomDto roomDto, Date screeningStartDate) {
        this.movieDto = movieDto;
        this.roomDto = roomDto;
        this.screeningStartDate = screeningStartDate;
    }

    public MovieDto getMovie() {
        return movieDto;
    }

    public RoomDto getRoom() {
        return roomDto;
    }

    public Date getScreeningStartDate() {
        return screeningStartDate;
    }
}
