package org.example.bookmyshow.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
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
    private List<Seat>seats;

}
