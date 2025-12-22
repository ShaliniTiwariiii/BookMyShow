package org.example.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity(name="Shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Theatre theatre;
    @ManyToOne
    private Screen screen;
    private Date time;
    @OneToMany(mappedBy="show")
    private List<ShowSeat> showSeats;
    @OneToMany(mappedBy="show")
    private List<ShowSeatType>showSeatTypes;
    @Enumerated
    private Language language;
    @Enumerated
    private List<Features> features;
}
