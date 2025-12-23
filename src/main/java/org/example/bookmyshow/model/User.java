package org.example.bookmyshow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User extends BaseModel{
private String name;

@OneToMany(mappedBy="bookedBy")
private List<Booking>bookings;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}
