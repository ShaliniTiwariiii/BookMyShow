package org.example.bookmyshow.services;


import org.example.bookmyshow.model.*;
import org.example.bookmyshow.repositories.BookingRepositrory;
import org.example.bookmyshow.repositories.ShowRepository;
import org.example.bookmyshow.repositories.ShowSeatRepository;
import org.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;


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
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookTicket(Long showId, Long userId, List<Long> showSeatIds) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));

        List<ShowSeat> showSeats =
                showSeatRepository.findAllByIdInAndStatus(
                        showSeatIds,
                        ShowSeatStatus.AVAILABLE
                );

        if (showSeats.size() < showSeatIds.size()) {
            throw new RuntimeException("Some seats are already booked");
        }

        Booking booking = new Booking();
        booking.setBookedBy(user);
        booking.setShow(show);
        booking.setBookingDate(new Date());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setNoOfSeats(showSeats.size());

        booking = bookingRepositrory.save(booking); // 🔥 save first

        for (ShowSeat seat : showSeats) {
            seat.setStatus(ShowSeatStatus.BOOKED);
            seat.setBooking(booking); // 🔥 critical
        }

        showSeatRepository.saveAll(showSeats);

        booking.setBookedSeats(showSeats);

        return booking;
    }

}
