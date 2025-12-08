package org.example.bookmyshow.repositories;

import org.example.bookmyshow.model.ShowSeat;
import org.example.bookmyshow.model.ShowSeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    List<ShowSeat> findAllByIdInAndStatus(Iterable<Long> ids, ShowSeatStatus status);

}
