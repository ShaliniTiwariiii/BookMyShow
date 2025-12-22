package org.example.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;
import java.util.List;

@Entity
@Getter
@Setter
public class Screen extends BaseModel{
    private String name;
    @Enumerated
    @ElementCollection
    private List<Features> screenFeatures;
    @OneToMany
    @JoinColumn(name="screen_id")
    private List<Seat>seats;

}
