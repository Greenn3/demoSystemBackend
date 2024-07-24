package com.example.demosystembacjend.Repositories;

import com.example.demosystembacjend.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
List<Booking> findBookingByArrivalDate(LocalDate arrivalDate);

List<Booking> findBookingByDepartureDate(LocalDate departureDate);
}
