package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.model.Movie;
import com.epam.training.ticketservice.core.room.model.Room;

import java.util.Date;

public class Screening {

    private final Movie movie;
    private final Room room;
    private final Date screeningStartDate;

    public Screening(Movie movie, Room room, Date screeningStartDate) {
        this.movie = movie;
        this.room = room;
        this.screeningStartDate = screeningStartDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public Date getScreeningStartDate() {
        return screeningStartDate;
    }
}
