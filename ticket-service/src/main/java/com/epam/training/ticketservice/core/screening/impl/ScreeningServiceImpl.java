package com.epam.training.ticketservice.core.screening.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.screening.exception.OccupiedRoomException;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.exception.ScreeningBreakException;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import com.epam.training.ticketservice.core.screening.persistence.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final MovieService movieService;
    private final RoomService roomService;

    public ScreeningServiceImpl(ScreeningRepository screeningRepository,
                                MovieService movieService,
                                RoomService roomService) {
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
        Objects.requireNonNull(screeningDto.getScreeningStartDate(), "Screening start date cannot be null");

        List<ScreeningDto> sameRoomScreenings = getScreeningsByRoomName(screeningDto.getRoomDto().getRoomName());
        LocalDateTime newScreeningStart = screeningDto.getScreeningStartDate();
        int newScreeningLength = screeningDto.getMovieDto().getLengthInMinutes();

        for (var storedScreening : sameRoomScreenings) {
            LocalDateTime storedScreeningStart = storedScreening.getScreeningStartDate();
            int storedScreeningLength = storedScreening.getMovieDto().getLengthInMinutes();

            if (newScreeningStart.isAfter(storedScreeningStart.minusMinutes(newScreeningLength + 10))
                    && (newScreeningStart.isBefore(storedScreeningStart.plusMinutes(storedScreeningLength))
                    || newScreeningStart.isEqual(storedScreeningStart.plusMinutes(storedScreeningLength)))) {
                throw new OccupiedRoomException("There is an overlapping screening");
            } else if (newScreeningStart.isAfter(storedScreeningStart.plusMinutes(storedScreeningLength))
                    && newScreeningStart.isBefore(storedScreeningStart.plusMinutes(storedScreeningLength + 10))
                    || newScreeningStart.isEqual(storedScreeningStart.plusMinutes(storedScreeningLength + 10))) {
                throw new ScreeningBreakException(
                        "This would start in the break period after another screening in this room");
            }
        }

        Screening screening = new Screening(screeningDto.getMovieDto().getTitle(),
                screeningDto.getRoomDto().getRoomName(),
                screeningDto.getScreeningStartDate());
        screeningRepository.save(screening);
    }

    @Override
    public void deleteScreening(String title, String roomName, LocalDateTime screeningStartDate) {
        Objects.requireNonNull(title, "Movie title cannot be null");
        Objects.requireNonNull(roomName, "Room name cannot be null");
        Objects.requireNonNull(screeningStartDate, "Screening start date cannot be null");
        screeningRepository.deleteByTitleAndRoomNameAndScreeningStartDate(title, roomName, screeningStartDate);
    }

    private List<ScreeningDto> getScreeningsByRoomName(String roomName) {
        return screeningRepository.findByRoomName(roomName)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private ScreeningDto convertEntityToDto(Screening screening) {
        Optional<MovieDto> movieDto = movieService.getMovieByTitle(screening.getTitle());
        Optional<RoomDto> roomDto = roomService.getRoomByRoomName(screening.getRoomName());

        if (movieDto.isEmpty() || roomDto.isEmpty()) {
            throw new NullPointerException("One or more movies and/or rooms were probably deleted");
        }

        return ScreeningDto.builder()
                .movieDto(movieDto.get())
                .roomDto(roomDto.get())
                .screeningStartDate(screening.getScreeningStartDate())
                .build();
    }
}
