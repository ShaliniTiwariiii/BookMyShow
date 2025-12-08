package org.example.bookmyshow.controller;

import org.example.bookmyshow.dto.BookTicketResponse;
import org.example.bookmyshow.dto.BookTicketRequest;
import org.example.bookmyshow.dto.ResponseStatus;
import org.example.bookmyshow.model.Booking;
import org.example.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService= bookingService;
    }
    public BookTicketResponse bookTicket(BookTicketRequest request) {
        BookTicketResponse response =new BookTicketResponse();
        try{
            Booking booking= bookingService.bookTicket(
                    request.getShowId(),
                    request.getUserId(),
                    request.getShowSeats()
            );
            response.setBookingId(booking.getId());
            response.setSatus(ResponseStatus.SUCCESS);
            response.setMessage("Booking confirmed successfully.Make the payment");
        }catch(Exception e){
                response.setMessage("Something went wrong. Please try again");
                response.setSatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
