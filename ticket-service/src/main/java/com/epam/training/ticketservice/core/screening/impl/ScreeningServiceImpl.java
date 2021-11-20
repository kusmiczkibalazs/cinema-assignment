package com.epam.training.ticketservice.core.screening.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistence.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieService movieService;
    private final RoomService roomService;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository, MovieService movieService, RoomService roomService) {
        this.screeningRepository = screeningRepository;
        this.movieService = movieService;
        this.roomService = roomService;
    }

    @Override
    public List<ScreeningDto> getScreeningList() {
        return screeningRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public void createScreening(ScreeningDto screeningDto) {
        Objects.requireNonNull(screeningDto, "Screening cannot be null");
        Objects.requireNonNull(screeningDto.getMovieDto().getTitle(), "Movie title cannot be null");
        Objects.requireNonNull(screeningDto.getRoomDto().getRoomName(), "Room name cannot be null");
        Objects.requireNonNull(screeningDto.getScreeningStartDate(), "Screening start date cannot be null");

        Screening screening = new Screening(screeningDto.getMovieDto().getTitle(),
                screeningDto.getRoomDto().getRoomName(),
                screeningDto.getScreeningStartDate());
        screeningRepository.save(screening);
    }

    @Override
    public void deleteScreening(String title, String roomName, LocalDateTime screeningStartDate) {
        screeningRepository.deleteByTitleAndRoomNameAndScreeningStartDate(title, roomName, screeningStartDate);
    }

    private ScreeningDto convertEntityToDto(Screening screening) {
        return ScreeningDto.builder()
                .movieDto(movieService.getMovieByTitle(screening.getTitle()).get())
                .roomDto(roomService.getRoomByRoomName(screening.getRoomName()).get())
                .screeningStartDate(screening.getScreeningStartDate())
                .build();

        //TODO NULLCHECK
    }
}
