package org.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShowResponseDto {
    private Long showId;
    private String status;
    private String message;
}

