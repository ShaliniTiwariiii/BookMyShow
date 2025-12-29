package org.example.bookmyshow.repositories;

import org.example.bookmyshow.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository  extends JpaRepository<Theatre,Long> {
}
