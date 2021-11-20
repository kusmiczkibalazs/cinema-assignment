package com.epam.training.ticketservice.core.screening.persistence.repository;

import com.epam.training.ticketservice.core.screening.persistence.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {

    @Transactional
    void deleteByTitleAndRoomNameAndScreeningStartDate(String title, String roomName, LocalDateTime screeningStartDate);
}
