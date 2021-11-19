package com.epam.training.ticketservice.core.room.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Room {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String roomName;
    private int seatRowsCount;
    private int seatColumnsCount;

    public Room(String roomName, int seatRowsCount, int seatColumnsCount) {
        this.roomName = roomName;
        this.seatRowsCount = seatRowsCount;
        this.seatColumnsCount = seatColumnsCount;
    }
}
