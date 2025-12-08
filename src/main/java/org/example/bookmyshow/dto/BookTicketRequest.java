package org.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookTicketRequest {
    private Long showId;
    private Long userId;
    private List<Long> showSeats;
}
