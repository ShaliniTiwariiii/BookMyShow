package org.example.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String name;
    private int seatRow;
    private int seatCol;
   @ManyToOne
    private SeatType seatType;
}
