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
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScreeningServiceImplTest {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final MovieDto INTERSTELLAR_DTO = MovieDto.builder()
            .title("Interstellar")
            .genre("sci-fi")
            .lengthInMinutes(169)
            .build();
    private static final MovieDto TENET_DTO = MovieDto.builder()
            .title("Tenet")
            .genre("action")
            .lengthInMinutes(150)
            .build();

    private static final RoomDto PEDERSOLI_DTO = RoomDto.builder()
            .roomName("Pedersoli")
            .seatRowsCount(10)
            .seatColumnsCount(20)
            .build();
    private static final RoomDto LUMIERE_DTO = RoomDto.builder()
            .roomName("Lumiere")
            .seatRowsCount(5)
            .seatColumnsCount(9)
            .build();

    private static final Screening INTERSTELLAR_SCREENING_ENTITY = new Screening("Interstellar", "Pedersoli", LocalDateTime.parse("2021-11-25 14:00", formatter));
    private static final Screening TENET_SCREENING_ENTITY = new Screening("Tenet", "Lumiere", LocalDateTime.parse("2021-11-26 14:00", formatter));

    private final ScreeningDto INTERSTELLAR_SCREENING_DTO = ScreeningDto.builder()
            .movieDto(INTERSTELLAR_DTO)
            .roomDto(PEDERSOLI_DTO)
            .screeningStartDate(LocalDateTime.parse("2021-11-25 14:00", formatter))
            .build();

    private final ScreeningDto TENET_SCREENING_DTO = ScreeningDto.builder()
            .movieDto(TENET_DTO)
            .roomDto(LUMIERE_DTO)
            .screeningStartDate(LocalDateTime.parse("2021-11-26 14:00", formatter))
            .build();

    private final ScreeningRepository screeningRepository = mock(ScreeningRepository.class);
    private final MovieService movieService = mock(MovieService.class);
    private final RoomService roomService = mock(RoomService.class);
    private final ScreeningService underTest = new ScreeningServiceImpl(screeningRepository, movieService, roomService);


    @Test
    void testGetScreeningListShouldCallScreeningRepositoryAndReturnADtoList() {
        // Given
        when(movieService.getMovieByTitle("Interstellar")).thenReturn(Optional.of(INTERSTELLAR_DTO));
        when(movieService.getMovieByTitle("Tenet")).thenReturn(Optional.of(TENET_DTO));
        when(roomService.getRoomByRoomName("Pedersoli")).thenReturn(Optional.of(PEDERSOLI_DTO));
        when(roomService.getRoomByRoomName("Lumiere")).thenReturn(Optional.of(LUMIERE_DTO));
        when(screeningRepository.findAll()).thenReturn(List.of(INTERSTELLAR_SCREENING_ENTITY, TENET_SCREENING_ENTITY));
        List<ScreeningDto> expected = List.of(INTERSTELLAR_SCREENING_DTO, TENET_SCREENING_DTO);

        // When
        List<ScreeningDto> actual = underTest.getScreeningList();

        // Then
        assertEquals(expected, actual);
        verify(screeningRepository).findAll();
    }

    @Test
    void createScreeningShouldCallScreeningRepositoryWhenTheInputScreeningIsValid() {
        // Given
        when(screeningRepository.save(TENET_SCREENING_ENTITY)).thenReturn(TENET_SCREENING_ENTITY);

        // When
        underTest.createScreening(TENET_SCREENING_DTO);

        // Then
        verify(screeningRepository).save(TENET_SCREENING_ENTITY);
    }

    @Test
    void createScreeningShouldThrowNullPointerExceptionWhenScreeningIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.createScreening(null));
    }

    @Test
    void createScreeningShouldThrowNullPointerExceptionWhenScreeningStartDateIsNull() {
        // Given
        ScreeningDto screeningDto = ScreeningDto.builder()
                .movieDto(TENET_DTO)
                .roomDto(LUMIERE_DTO)
                .screeningStartDate(null)
                .build();

        // When - Then
        assertThrows(NullPointerException.class, () -> underTest.createScreening(screeningDto));
    }

    @Test
    void testCreateScreeningShouldThrowOccupiedRoomExceptionWhenAScreeningStartsAfterAnother() {
        // Given
        when(screeningRepository.findByRoomName("Pedersoli")).thenReturn(List.of(INTERSTELLAR_SCREENING_ENTITY));
        when(movieService.getMovieByTitle("Interstellar")).thenReturn(Optional.of(INTERSTELLAR_DTO));
        when(roomService.getRoomByRoomName("Pedersoli")).thenReturn(Optional.of(PEDERSOLI_DTO));

        ScreeningDto screeningDto = ScreeningDto.builder()
                .movieDto(TENET_DTO)
                .roomDto(PEDERSOLI_DTO)
                .screeningStartDate(LocalDateTime.parse("2021-11-25 14:05", formatter))
                .build();

        // When - Then
        assertThrows(OccupiedRoomException.class, () -> underTest.createScreening(screeningDto));
    }

    @Test
    void testCreateScreeningShouldThrowOccupiedRoomExceptionWhenAScreeningStartsAfterAnother22222() {
        // Given
        when(screeningRepository.findByRoomName("Pedersoli")).thenReturn(List.of(INTERSTELLAR_SCREENING_ENTITY));
        when(movieService.getMovieByTitle("Interstellar")).thenReturn(Optional.of(INTERSTELLAR_DTO));
        when(roomService.getRoomByRoomName("Pedersoli")).thenReturn(Optional.of(PEDERSOLI_DTO));

        ScreeningDto screeningDto = ScreeningDto.builder()
                .movieDto(TENET_DTO)
                .roomDto(PEDERSOLI_DTO)
                .screeningStartDate(LocalDateTime.parse("2021-11-25 12:00", formatter))
                .build();

        // When - Then
        assertThrows(OccupiedRoomException.class, () -> underTest.createScreening(screeningDto));
    }

    @Test
    void testCreateScreeningShouldThrowOccupiedRoomExceptionWhenAScreeningStartsExactlyAtTheEndOfAnother() {
        // Given
        when(screeningRepository.findByRoomName("Pedersoli")).thenReturn(List.of(INTERSTELLAR_SCREENING_ENTITY));
        when(movieService.getMovieByTitle("Interstellar")).thenReturn(Optional.of(INTERSTELLAR_DTO));
        when(roomService.getRoomByRoomName("Pedersoli")).thenReturn(Optional.of(PEDERSOLI_DTO));

        ScreeningDto screeningDto = ScreeningDto.builder()
                .movieDto(TENET_DTO)
                .roomDto(PEDERSOLI_DTO)
                .screeningStartDate(LocalDateTime.parse("2021-11-25 16:49", formatter))
                .build();

        // When - Then
        assertThrows(OccupiedRoomException.class, () -> underTest.createScreening(screeningDto));
    }

    @Test
    void testCreateScreeningShouldThrowScreeningBreakExceptionWhenAScreeningStartsInTheBreakOfAnother() {
        // Given
        when(screeningRepository.findByRoomName("Pedersoli")).thenReturn(List.of(INTERSTELLAR_SCREENING_ENTITY));
        when(movieService.getMovieByTitle("Interstellar")).thenReturn(Optional.of(INTERSTELLAR_DTO));
        when(roomService.getRoomByRoomName("Pedersoli")).thenReturn(Optional.of(PEDERSOLI_DTO));

        ScreeningDto screeningDto = ScreeningDto.builder()
                .movieDto(TENET_DTO)
                .roomDto(PEDERSOLI_DTO)
                .screeningStartDate(LocalDateTime.parse("2021-11-25 16:59", formatter))
                .build();

        // When - Then
        assertThrows(ScreeningBreakException.class, () -> underTest.createScreening(screeningDto));
    }

    @Test
    void testCreateScreeningShouldThrowScreeningBreakExceptionWhenAScreeningStartsExactlyAtTheEndOfABreak() {
        // Given
        when(screeningRepository.findByRoomName("Pedersoli")).thenReturn(List.of(INTERSTELLAR_SCREENING_ENTITY));
        when(movieService.getMovieByTitle("Interstellar")).thenReturn(Optional.of(INTERSTELLAR_DTO));
        when(roomService.getRoomByRoomName("Pedersoli")).thenReturn(Optional.of(PEDERSOLI_DTO));

        ScreeningDto screeningDto = ScreeningDto.builder()
                .movieDto(TENET_DTO)
                .roomDto(PEDERSOLI_DTO)
                .screeningStartDate(LocalDateTime.parse("2021-11-25 16:55", formatter))
                .build();

        // When - Then
        assertThrows(ScreeningBreakException.class, () -> underTest.createScreening(screeningDto));
    }

    @Test
    void testDeleteScreeningShouldCallScreeningRepositoryWhenTheInputScreeningIsValid() {
        // Given
        when(screeningRepository.save(TENET_SCREENING_ENTITY)).thenReturn(TENET_SCREENING_ENTITY);

        // When
        underTest.deleteScreening("Tenet", "Lumiere", LocalDateTime.parse("2021-11-26 14:00", formatter));

        // Then
        verify(screeningRepository).deleteByTitleAndRoomNameAndScreeningStartDate("Tenet", "Lumiere", LocalDateTime.parse("2021-11-26 14:00", formatter));
    }

    @Test
    void testDeleteScreeningShouldThrowNullPointerExceptionWhenMovieTitleIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.deleteScreening(null, "Lumiere", LocalDateTime.parse("2021-11-26 14:00", formatter)));
    }

    @Test
    void testDeleteScreeningShouldThrowNullPointerExceptionWhenRoomNameIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.deleteScreening("Tenet", null, LocalDateTime.parse("2021-11-26 14:00", formatter)));
    }

    @Test
    void testDeleteScreeningShouldThrowNullPointerExceptionWhenScreeningStartDateIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.deleteScreening("Tenet", "Lumiere", null));
    }
}