package org.example.bookmyshow.services;

import org.example.bookmyshow.model.*;
import org.example.bookmyshow.repositories.BookingRepositrory;
import org.example.bookmyshow.repositories.ShowRepository;
import org.example.bookmyshow.repositories.ShowSeatRepository;
import org.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private ShowRepository showRepository;
    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepositrory bookingRepositrory;
    @Autowired
    public BookingService(
            ShowRepository showRepository,
            UserRepository userRepository,
            ShowSeatRepository showSeatRepository,
            BookingRepositrory bookingRepositrory
    ){
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepositrory = bookingRepositrory;
    }
    public Booking bookTicket(
            Long showId,
            Long userId,
            List<Long>showSeatIds
    ){
        Optional<User> userOptional=userRepository.findById(userId);
        User user=null;
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not found");
        }
        user=userOptional.get();
        Optional< Show> showOptional= showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException("Show not found");
        }
        Show show=showOptional.get();
        List<ShowSeat>showSeats= showSeatRepository.findAllByIdInAndStatus(showSeatIds, ShowSeatStatus.AVAILABLE);
       if(showSeats.size()<showSeatIds.size()){
              throw new RuntimeException("Some seats are already booked. Please choose different seats");
       }
       for(ShowSeat showSeat:showSeats){
              showSeat.setStatus(ShowSeatStatus.BOOKED);
       }
       showSeatRepository.saveAll(showSeats);
       Booking booking=new Booking();
       booking.setBookedBy(user);
       booking.setBookingDate(new Date());
       booking.setBookedSeats(showSeats);
       booking.setBookingStatus(BookingStatus.PENDING);
//       booking.setTotalAmount();
       return new Booking();
    }
}
