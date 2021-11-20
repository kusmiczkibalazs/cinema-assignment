package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@AllArgsConstructor
@Builder
@Data
public class ScreeningDto {

    private final MovieDto movieDto;
    private final RoomDto roomDto;
    private final LocalDateTime screeningStartDate;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String screeningStartDateString = screeningStartDate.format(formatter);
        return movieDto.toString() + ", screened in room " + roomDto.getRoomName() + ", at " + screeningStartDateString;
    }
}
