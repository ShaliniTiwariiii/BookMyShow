package org.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateScreenRequestDto {
    private String name;
    private Long theatreId;
}
