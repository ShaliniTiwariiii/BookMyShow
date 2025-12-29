package org.example.bookmyshow.services;


import org.example.bookmyshow.model.Screen;
import org.example.bookmyshow.repositories.ScreenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScreenService {

    private final ScreenRepository screenRepository;

    public ScreenService(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    public Screen createScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    public Screen getScreenById(Long id) {
        Optional<Screen> opt = screenRepository.findById(id);

        if (opt.isEmpty()) {
            throw new RuntimeException("Screen not found with id: " + id);
        }

        return opt.get();
    }
}

