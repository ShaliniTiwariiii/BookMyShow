package org.example.bookmyshow.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
class SeatType  extends  BaseModel{
    private String name;
}
