package com.example.demosystembacjend;

import com.example.demosystembacjend.Entities.Booking;
import com.example.demosystembacjend.Repositories.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class BookingConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(BookingRepository repository){
        return args -> {
Booking booking2 = new Booking(
        "Marciniak",
        LocalDate.of(2024, Month.FEBRUARY, 4),
        LocalDate.of(2024, Month.FEBRUARY, 9)
);
//repository.save(booking2);
        };
    }
}
