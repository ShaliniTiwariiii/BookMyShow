package org.example.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {

    private Date bookingDate;

    private int noOfSeats;

    @ManyToOne
    private User bookedBy;

    @ManyToOne
    private Show show;

    @OneToMany(mappedBy = "booking")
    private List<ShowSeat> bookedSeats; // ✅ one booking → many seats

    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}

