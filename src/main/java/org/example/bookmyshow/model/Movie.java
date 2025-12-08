package org.example.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Movie extends BaseModel{
    private String title;
    private String director;
    private String year;
    private String genre;
    @ManyToMany
    private List<Artist> artists;
    @Enumerated
    @ElementCollection
    private List<Features> features;
    @Enumerated
    @ElementCollection
    private List<Language> languages;

}
