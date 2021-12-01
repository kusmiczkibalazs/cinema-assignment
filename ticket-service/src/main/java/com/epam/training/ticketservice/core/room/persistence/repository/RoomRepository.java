package com.epam.training.ticketservice.core.room.persistence.repository;

import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    Optional<Room> findByRoomName(String roomName);

    @Transactional
    @Modifying
    @Query("UPDATE Room r "
            + "SET r.seatRowsCount = :newSeatRowsCount, r.seatColumnsCount = :newSeatColumnsCount "
            + "WHERE r.roomName = :roomName")
    Room updateRoom(@Param("roomName") String roomName,
                    @Param("newSeatRowsCount") int newSeatRowsCount,
                    @Param("newSeatColumnsCount") int newSeatColumnsCount);

    @Transactional
    Room deleteByRoomName(String roomName);
}
