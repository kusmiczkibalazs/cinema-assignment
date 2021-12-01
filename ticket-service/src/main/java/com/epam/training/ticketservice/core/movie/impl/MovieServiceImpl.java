package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.movie.persistence.entity.Movie;
import com.epam.training.ticketservice.core.movie.persistence.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> getMovieList() {
        return movieRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<MovieDto> getMovieByTitle(String title) {
        return convertEntityToDto(movieRepository.findByTitle(title));
    }

    @Override
    public void createMovie(MovieDto movieDto) {
        Objects.requireNonNull(movieDto, "Movie cannot be null");
        Objects.requireNonNull(movieDto.getTitle(), "Movie title cannot be null");
        Objects.requireNonNull(movieDto.getGenre(), "Movie genre cannot be null");

        Movie movie = new Movie(movieDto.getTitle(),
                movieDto.getGenre(),
                movieDto.getLengthInMinutes());
        movieRepository.save(movie);
    }


    @Override
    public void updateMovie(MovieDto movieDto) {
        Objects.requireNonNull(movieDto, "Movie cannot be null");
        Objects.requireNonNull(movieDto.getTitle(), "Movie title cannot be null");
        Objects.requireNonNull(movieDto.getGenre(), "Movie genre cannot be null");

        movieRepository.updateMovie(movieDto.getTitle(), movieDto.getGenre(), movieDto.getLengthInMinutes());
    }

    @Override
    public void deleteMovie(String title) {
        Objects.requireNonNull(title, "Movie title cannot be null");
        movieRepository.deleteByTitle(title);
    }

    private MovieDto convertEntityToDto(Movie movie) {
        return MovieDto.builder()
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .lengthInMinutes(movie.getLengthInMinutes())
                .build();
    }

    private Optional<MovieDto> convertEntityToDto(Optional<Movie> movie) {
        return movie.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(movie.get()));
    }
}
