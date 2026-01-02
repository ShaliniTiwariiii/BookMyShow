package org.example.bookmyshow.controller;

import org.example.bookmyshow.dto.CreateShowRequestDto;
import org.example.bookmyshow.model.Show;
import org.example.bookmyshow.services.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")

public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody CreateShowRequestDto request) {
        Show show = showService.createShow(request);
        return ResponseEntity.ok(show);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowsByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(showService.getShowsByMovie(movieId));
    }
}

