package com.epam.training.ticketservice.core.screening.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Screening {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String roomName;
    private LocalDateTime screeningStartDate;

    public Screening(String title, String roomName, LocalDateTime screeningStartDate) {
        this.title = title;
        this.roomName = roomName;
        this.screeningStartDate = screeningStartDate;
    }
}
