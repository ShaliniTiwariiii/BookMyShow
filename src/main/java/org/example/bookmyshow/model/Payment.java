package org.example.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Auditable;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private Date date;
    private String refNumber;
    @Enumerated
    private PaymentStatus status;
    @Enumerated
    private PaymentMode paymentMode;
    @ManyToOne
    private Booking booking;
}
