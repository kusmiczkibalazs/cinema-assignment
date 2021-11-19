package com.epam.training.ticketservice.core.room.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class RoomDto {

    private final String roomName;
    private int seatRowsCount;
    private int seatColumnsCount;

    @Override
    public String toString() {
        return "Room " + roomName + " with " + (seatRowsCount * seatColumnsCount) + " seats, "
                + seatRowsCount + " rows and " + seatColumnsCount + " columns";
    }
}
