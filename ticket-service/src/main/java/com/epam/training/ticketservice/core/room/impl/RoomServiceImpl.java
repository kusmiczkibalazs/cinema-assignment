package com.epam.training.ticketservice.core.room.impl;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import com.epam.training.ticketservice.core.room.persistence.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDto> getRoomList() {
        return roomRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<RoomDto> getRoomByRoomName(String roomName) {
        return convertEntityToDto(roomRepository.findByRoomName(roomName));
    }

    @Override
    public void createRoom(RoomDto roomDto) {
        Objects.requireNonNull(roomDto, "Room cannot be null");
        Objects.requireNonNull(roomDto.getRoomName(), "Room name cannot be null");

        Room room = new Room(roomDto.getRoomName(),
                roomDto.getSeatRowsCount(),
                roomDto.getSeatColumnsCount());
        roomRepository.save(room);
    }

    @Override
    public void updateRoom(RoomDto roomDto) {
        Objects.requireNonNull(roomDto, "Room cannot be null");
        Objects.requireNonNull(roomDto.getRoomName(), "Room name cannot be null");

        roomRepository.updateRoom(roomDto.getRoomName(), roomDto.getSeatRowsCount(), roomDto.getSeatColumnsCount());
    }

    @Override
    public void deleteRoom(String roomName) {
        roomRepository.deleteByRoomName(roomName);
    }

    private RoomDto convertEntityToDto(Room room) {
        return RoomDto.builder()
                .roomName(room.getRoomName())
                .seatRowsCount(room.getSeatRowsCount())
                .seatColumnsCount(room.getSeatColumnsCount())
                .build();
    }

    private Optional<RoomDto> convertEntityToDto(Optional<Room> room) {
        return room.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(room.get()));
    }
}
