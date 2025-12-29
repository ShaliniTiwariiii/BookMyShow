package org.example.bookmyshow.controller;


import org.example.bookmyshow.dto.CreateTheatreRequestDto;
import org.example.bookmyshow.dto.TheatreResponseDto;
import org.example.bookmyshow.model.Theatre;
import org.example.bookmyshow.services.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatres")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping
    public ResponseEntity<TheatreResponseDto> createTheatre(
            @RequestBody CreateTheatreRequestDto request) {

        Theatre theatre = new Theatre();
        theatre.setName(request.getName());
        theatre.setAddress(request.getAddress());

        Theatre saved = theatreService.createTheatre(theatre);

        TheatreResponseDto response = new TheatreResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setAddress(saved.getAddress());

        return ResponseEntity.ok(response);
    }
}

