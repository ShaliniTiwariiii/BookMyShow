package org.example.bookmyshow.controller;

import org.example.bookmyshow.dto.BookTicketRequest;
import org.example.bookmyshow.dto.BookTicketResponse;
import org.example.bookmyshow.dto.ResponseStatus;
import org.example.bookmyshow.model.Booking;
import org.example.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookTicketResponse> bookTicket(
            @RequestBody BookTicketRequest request) {

        BookTicketResponse response = new BookTicketResponse();

        try {
            Booking booking = bookingService.bookTicket(
                    request.getShowId(),
                    request.getUserId(),
                    request.getShowSeats()
            );

            response.setBookingId(booking.getId());
            response.setSatus(ResponseStatus.SUCCESS);
            response.setMessage("Booking confirmed successfully");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setSatus(ResponseStatus.FAILURE);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }
}
