package com.epam.training.ticketservice.core.movie;

public class Movie {

    private final String title;
    private String genre;
    private int lengthInMinutes;

    public Movie(String title, String genre, int lengthInMinutes) {
        this.title = title;
        this.genre = genre;
        this.lengthInMinutes = lengthInMinutes;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

}
