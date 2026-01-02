package org.example.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity(name="Shows")
public class Show extends BaseModel {

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Screen screen;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int price;

    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeats;

    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypes;

    @Enumerated(EnumType.STRING)
    private Language language;

    @ElementCollection(targetClass = Features.class)
    @Enumerated(EnumType.STRING)
    private List<Features> features;
}

