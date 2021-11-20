package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.screening.model.ScreeningDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningService {

    List<ScreeningDto> getScreeningList();

    void createScreening(ScreeningDto screeningDto);

    void deleteScreening(String title, String roomName, LocalDateTime screeningStartDate);
}
