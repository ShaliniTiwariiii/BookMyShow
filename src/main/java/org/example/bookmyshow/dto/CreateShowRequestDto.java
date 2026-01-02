package org.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CreateShowRequestDto {

    private Long movieId;
    private Long screenId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private int price;

    // Optional — if you want price category wise
    private List<String> seatTypes;
}
