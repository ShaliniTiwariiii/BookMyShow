package org.example.bookmyshow.services;


import org.example.bookmyshow.model.Theatre;
import org.example.bookmyshow.repositories.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    // CREATE
    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    // GET BY ID
    public Theatre getTheatreById(Long id) {
        Optional<Theatre> opt = theatreRepository.findById(id);

        if (opt.isEmpty()) {
            throw new RuntimeException("Theatre not found with id: " + id);
        }

        return opt.get();
    }
}

