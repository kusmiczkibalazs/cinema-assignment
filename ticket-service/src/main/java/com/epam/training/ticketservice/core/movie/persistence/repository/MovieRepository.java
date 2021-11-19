package com.epam.training.ticketservice.core.movie.persistence.repository;

import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Optional<Movie> findByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Movie m SET m.genre = :newGenre, m.lengthInMinutes = :newLengthInMinutes WHERE m.title = :title")
    void updateMovie(@Param("title") String title, @Param("newGenre") String genre, @Param("newLengthInMinutes") int lengthInMinutes);

    @Transactional
    void deleteByTitle(String title);
}
