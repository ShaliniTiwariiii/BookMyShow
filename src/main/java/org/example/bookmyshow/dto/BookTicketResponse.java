package org.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponse {
    private Long bookingId;
    private ResponseStatus satus;
    private String message;
}

