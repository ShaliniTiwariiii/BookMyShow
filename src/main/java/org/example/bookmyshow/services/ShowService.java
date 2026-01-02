package org.example.bookmyshow.services;

import lombok.RequiredArgsConstructor;
import org.example.bookmyshow.dto.CreateShowRequestDto;
import org.example.bookmyshow.model.Movie;
import org.example.bookmyshow.model.Screen;
import org.example.bookmyshow.model.Show;
import org.example.bookmyshow.repositories.MovieRepository;
import org.example.bookmyshow.repositories.ScreenRepository;
import org.example.bookmyshow.repositories.ShowRepository;
import org.example.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    public ShowService(ShowRepository showRepository,
                       MovieRepository movieRepository,
                       ScreenRepository screenRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }

    public Show createShow(CreateShowRequestDto request) {

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Screen screen = screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found"));

        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());
        show.setPrice(request.getPrice());

        return showRepository.save(show);
    }

    public List<Show> getShowsByMovie(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }
}
