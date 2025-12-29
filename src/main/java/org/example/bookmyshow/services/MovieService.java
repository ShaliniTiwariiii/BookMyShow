package org.example.bookmyshow.services;


import org.example.bookmyshow.model.Movie;
import org.example.bookmyshow.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // ---------- CREATE MOVIE ----------
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // ---------- GET MOVIE BY ID ----------
    public Movie getMovieById(Long id) {
        Optional<Movie> opt = movieRepository.findById(id);

        if (opt.isEmpty()) {
            throw new RuntimeException("Movie not found with id: " + id);
        }

        return opt.get();
    }
}
