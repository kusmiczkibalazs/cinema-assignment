package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceImplTest {

    private static final Movie INTERSTELLAR_ENTITY = new Movie("Interstellar", "sci-fi", 169);
    private static final Movie TENET_ENTITY = new Movie("Tenet", "action", 150);

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

    private final MovieRepository movieRepository = mock(MovieRepository.class);
    private final MovieService underTest = new MovieServiceImpl(movieRepository);

    @Test
    void testGetMovieListShouldCallMovieRepositoryAndReturnADtoList() {
        // Given
        when(movieRepository.findAll()).thenReturn(List.of(INTERSTELLAR_ENTITY, TENET_ENTITY));
        List<MovieDto> expected = List.of(INTERSTELLAR_DTO, TENET_DTO);

        // When
        List<MovieDto> actual = underTest.getMovieList();

        // Then
        assertEquals(expected, actual);
        verify(movieRepository).findAll();
    }

    @Test
    void testGetMovieByTitleShouldReturnATenetDtoWhenTheMovieExists() {
        // Given
        when(movieRepository.findByTitle("Tenet")).thenReturn(Optional.of(TENET_ENTITY));
        Optional<MovieDto> expected = Optional.of(TENET_DTO);

        // When
        Optional<MovieDto> actual = underTest.getMovieByTitle("Tenet");

        // Then
        assertEquals(expected, actual);
        verify(movieRepository).findByTitle("Tenet");
    }

    @Test
    void testGetMovieByTitleShouldReturnOptionalEmptyWhenInputMovieTitleDoesNotExist() {
        // Given
        when(movieRepository.findByTitle("dummy")).thenReturn(Optional.empty());
        Optional<MovieDto> expected = Optional.empty();

        // When
        Optional<MovieDto> actual = underTest.getMovieByTitle("dummy");

        // Then
        assertTrue(actual.isEmpty());
        assertEquals(expected, actual);
        verify(movieRepository).findByTitle("dummy");
    }

    @Test
    void testGetMovieByTitleShouldReturnOptionalEmptyWhenInputMovieTitleIsNull() {
        // Given
        when(movieRepository.findByTitle(null)).thenReturn(Optional.empty());
        Optional<MovieDto> expected = Optional.empty();

        // When
        Optional<MovieDto> actual = underTest.getMovieByTitle(null);

        // Then
        assertTrue(actual.isEmpty());
        assertEquals(expected, actual);
        verify(movieRepository).findByTitle(null);
    }

    @Test
    void testCreateMovieShouldCallMovieRepositoryWhenTheInputMovieIsValid() {
        // Given
        when(movieRepository.save(TENET_ENTITY)).thenReturn(TENET_ENTITY);

        // When
        underTest.createMovie(TENET_DTO);

        // Then
        verify(movieRepository).save(TENET_ENTITY);
    }

    @Test
    void testCreateMovieShouldThrowNullPointerExceptionWhenMovieIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.createMovie(null));
    }

    @Test
    void testCreateMovieShouldThrowNullPointerExceptionWhenMovieTitleIsNull() {
        // Given
        MovieDto movieDto = MovieDto.builder()
                .title(null)
                .genre("action")
                .lengthInMinutes(150)
                .build();

        // When - Then
        assertThrows(NullPointerException.class, () -> underTest.createMovie(movieDto));
    }

    @Test
    void testCreateMovieShouldThrowNullPointerExceptionWhenMovieGenreIsNull() {
        // Given
        MovieDto movieDto = MovieDto.builder()
                .title("Tenet")
                .genre(null)
                .lengthInMinutes(150)
                .build();

        // When - Then
        assertThrows(NullPointerException.class, () -> underTest.createMovie(movieDto));
    }

    @Test
    void testUpdateMovieShouldCallMovieRepositoryWhenTheInputMovieIsValid() {
        // Given
        when(movieRepository.save(TENET_ENTITY)).thenReturn(TENET_ENTITY);

        MovieDto UPDATED_TENET_DTO = MovieDto.builder()
                .title("Tenet")
                .genre("sci-fi")
                .lengthInMinutes(200)
                .build();

        // When
        underTest.updateMovie(UPDATED_TENET_DTO);

        // Then
        verify(movieRepository).updateMovie("Tenet", "sci-fi", 200);
    }

    @Test
    void testUpdateMovieShouldThrowNullPointerExceptionWhenMovieIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.updateMovie(null));
    }

    @Test
    void testUpdateMovieShouldThrowNullPointerExceptionWhenMovieTitleIsNull() {
        // Given
        MovieDto movieDto = MovieDto.builder()
                .title(null)
                .genre("action")
                .lengthInMinutes(150)
                .build();

        // When - Then
        assertThrows(NullPointerException.class, () -> underTest.updateMovie(movieDto));
    }

    @Test
    void testUpdateMovieShouldThrowNullPointerExceptionWhenMovieGenreIsNull() {
        // Given
        MovieDto movieDto = MovieDto.builder()
                .title("Tenet")
                .genre(null)
                .lengthInMinutes(150)
                .build();

        // When - Then
        assertThrows(NullPointerException.class, () -> underTest.updateMovie(movieDto));
    }

    @Test
    void testDeleteMovieShouldCallMovieRepositoryWhenTheInputMovieIsValid() {
        // Given
        when(movieRepository.save(TENET_ENTITY)).thenReturn(TENET_ENTITY);

        // When
        underTest.deleteMovie("Tenet");

        // Then
        verify(movieRepository).deleteByTitle("Tenet");
    }

    @Test
    void testDeleteMovieShouldThrowNullPointerExceptionWhenMovieTitleIsNull() {
        // Given - When - Then
        assertThrows(NullPointerException.class, () -> underTest.deleteMovie(null));
    }
}