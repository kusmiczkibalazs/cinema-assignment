package com.epam.training.ticketservice.core.room.impl;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    private static final Room PEDERSOLI_ENTITY = new Room("Pedersoli", 10, 20);
    private static final Room LUMIERE_ENTITY = new Room("Lumiere", 5, 9);

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

    private final RoomRepository roomRepository = mock(RoomRepository.class);
    private final RoomService underTest = new RoomServiceImpl(roomRepository);

    @Test
    void testGetRoomListShouldCallRoomRepositoryAndReturnADtoList() {
        // Given
        when(roomRepository.findAll()).thenReturn(List.of(PEDERSOLI_ENTITY, LUMIERE_ENTITY));
        List<RoomDto> expected = List.of(PEDERSOLI_DTO, LUMIERE_DTO);

        // When
        List<RoomDto> actual = underTest.getRoomList();

        // Then
        assertEquals(expected, actual);
        verify(roomRepository).findAll();
    }

    @Test
    void testGetRoomByRoomNameShouldReturnAPedersoliDtoWhenTheRoomExists() {
        // Given
        when(roomRepository.findByRoomName("Pedersoli")).thenReturn(Optional.of(PEDERSOLI_ENTITY));
        Optional<RoomDto> expected = Optional.of(PEDERSOLI_DTO);

        // When
        Optional<RoomDto> actual = underTest.getRoomByRoomName("Pedersoli");

        // Then
        assertEquals(expected, actual);
        verify(roomRepository).findByRoomName("Pedersoli");
    }

    @Test
    void testGetRoomByRoomNameShouldReturnOptionalEmptyWhenInputRoomNameDoesNotExist() {
        // Given
        when(roomRepository.findByRoomName("dummy")).thenReturn(Optional.empty());
        Optional<RoomDto> expected = Optional.empty();

        // When
        Optional<RoomDto> actual = underTest.getRoomByRoomName("dummy");

        // Then
        assertTrue(actual.isEmpty());
        assertEquals(expected, actual);
        verify(roomRepository).findByRoomName("dummy");
    }

    @Test
    void testGetRoomByRoomNameShouldReturnOptionalEmptyWhenInputRoomNameIsNull() {
        // Given
        when(roomRepository.findByRoomName(null)).thenReturn(Optional.empty());
        Optional<RoomDto> expected = Optional.empty();

        // When
        Optional<RoomDto> actual = underTest.getRoomByRoomName(null);

        // Then
        assertTrue(actual.isEmpty());
        assertEquals(expected, actual);
        verify(roomRepository).findByRoomName(null);
    }

    @Test
    void testCreateRoomShouldCallRoomRepositoryWhenTheInputRoomIsValid() {
        // Given
        when(roomRepository.save(PEDERSOLI_ENTITY)).thenReturn(PEDERSOLI_ENTITY);

        // When
        underTest.createRoom(PEDERSOLI_DTO);

        // Then
        verify(roomRepository).save(PEDERSOLI_ENTITY);
    }

    @Test
    void testCreateRoomShouldThrowNullPointerExceptionWhenRoomIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.createRoom(null));
    }

    @Test
    void testCreateRoomShouldThrowNullPointerExceptionWhenRoomNameIsNull() {
        // Given
        RoomDto roomDto = RoomDto.builder()
                .roomName(null)
                .seatRowsCount(10)
                .seatColumnsCount(20)
                .build();

        // When - Then
        assertThrows(NullPointerException.class, () -> underTest.createRoom(roomDto));
    }

    @Test
    void testUpdateRoomShouldCallRoomRepositoryWhenTheInputRoomIsValid() {
        // Given
        when(roomRepository.save(PEDERSOLI_ENTITY)).thenReturn(PEDERSOLI_ENTITY);

        RoomDto UPDATED_PEDERSOLI_DTO = RoomDto.builder()
                .roomName("Pedersoli")
                .seatRowsCount(20)
                .seatColumnsCount(30)
                .build();

        // When
        underTest.updateRoom(UPDATED_PEDERSOLI_DTO);

        // Then
        verify(roomRepository).updateRoom("Pedersoli", 20, 30);
    }

    @Test
    void testUpdateRoomShouldThrowNullPointerExceptionWhenRoomIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.updateRoom(null));
    }

    @Test
    void testUpdateRoomShouldThrowNullPointerExceptionWhenRoomNameIsNull() {
        // Given
        RoomDto roomDto = RoomDto.builder()
                .roomName(null)
                .seatRowsCount(10)
                .seatColumnsCount(20)
                .build();

        // When - Then
        assertThrows(NullPointerException.class, () -> underTest.updateRoom(roomDto));
    }

    @Test
    void testDeleteRoomShouldCallRoomRepositoryWhenTheInputRoomIsValid() {
        // Given
        when(roomRepository.save(PEDERSOLI_ENTITY)).thenReturn(PEDERSOLI_ENTITY);

        // When
        underTest.deleteRoom("Pedersoli");

        // Then
        verify(roomRepository).deleteByRoomName("Pedersoli");
    }

    @Test
    void testDeleteRoomShouldThrowNullPointerExceptionWhenRoomNameIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.deleteRoom(null));
    }
}