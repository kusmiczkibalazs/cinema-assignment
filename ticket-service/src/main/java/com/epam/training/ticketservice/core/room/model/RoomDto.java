package com.epam.training.ticketservice.core.room.model;

public class RoomDto {

    private final String roomName;
    private int seatRowsCount;
    private int seatColumnsCount;

    public RoomDto(String roomName, int seatRowsCount, int seatColumnsCount) {
        this.roomName = roomName;
        this.seatRowsCount = seatRowsCount;
        this.seatColumnsCount = seatColumnsCount;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getSeatRowsCount() {
        return seatRowsCount;
    }

    public int getSeatColumnsCount() {
        return seatColumnsCount;
    }

    public void setSeatRowsCount(int seatRowsCount) {
        this.seatRowsCount = seatRowsCount;
    }

    public void setSeatColumnsCount(int seatColumnsCount) {
        this.seatColumnsCount = seatColumnsCount;
    }
}
