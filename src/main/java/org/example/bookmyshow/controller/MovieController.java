package org.example.bookmyshow.controller;

import org.example.bookmyshow.dto.CreateMovieRequestDto;
import org.example.bookmyshow.dto.MovieResponseDto;
import org.example.bookmyshow.model.Movie;
import org.example.bookmyshow.services.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(
            @RequestBody CreateMovieRequestDto request) {

        Movie movie = new Movie();
        movie.setTitle(request.getName());

        Movie saved = movieService.createMovie(movie);

        MovieResponseDto response = new MovieResponseDto();

        response.setId(saved.getId());
        response.setName(saved.getTitle());

        return ResponseEntity.ok(response);
    }
}
