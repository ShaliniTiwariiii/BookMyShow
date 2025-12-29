package org.example.bookmyshow.controller;


import org.example.bookmyshow.dto.CreateScreenRequestDto;
import org.example.bookmyshow.dto.ScreenResponseDto;
import org.example.bookmyshow.model.Screen;
import org.example.bookmyshow.model.Theatre;
import org.example.bookmyshow.services.ScreenService;
import org.example.bookmyshow.services.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/screens")
public class ScreenController {

    private final ScreenService screenService;
    private final TheatreService theatreService;

    public ScreenController(ScreenService screenService,
                            TheatreService theatreService) {
        this.screenService = screenService;
        this.theatreService = theatreService;
    }

    @PostMapping
    public ResponseEntity<ScreenResponseDto> createScreen(
            @RequestBody CreateScreenRequestDto request) {

        Theatre theatre = theatreService.getTheatreById(request.getTheatreId());

        Screen screen = new Screen();
        screen.setName(request.getName());
        screen.setTheatre(theatre);

        Screen saved = screenService.createScreen(screen);

        ScreenResponseDto response = new ScreenResponseDto();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setTheatreId(saved.getTheatre().getId());

        return ResponseEntity.ok(response);
    }
}

