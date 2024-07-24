package com.example.demosystembacjend.Entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue
   private Integer id;

    private String name;
    @Column(name = "arrival_date")
  private LocalDate arrivalDate;
    @Column(name = "departure_date")
   private LocalDate departureDate;

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
     AccType accType;
    @Column(name = "info")
    private String info;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "has_arrived")
    private boolean hasArrived = false;
    @Column(name = "is_paid")
    private boolean isPaid = false;
    @Column(name = "has_left")
    private boolean hasLeft = false;


    public Booking(String name, LocalDate arrivalDate, LocalDate departureDate) {
        this.name = name;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
    }

    public Booking(String name, LocalDate arrivalDate, LocalDate departureDate, AccType accTypeFK) {
        this.name = name;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.accType = accTypeFK;
    }

    public Booking(String name, LocalDate arrivalDate, LocalDate departureDate,
                   AccType accTypeFK, String phone, String email, String info) {
        this.name = name;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.accType = accTypeFK;
        this.phone = phone;
        this.email = email;
        this.info = info;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public AccType getAccTypeFK() {
        return accType;
    }

    public void setAccTypeFK(AccType accTypeFK) {
        this.accType = accTypeFK;
    }
}
