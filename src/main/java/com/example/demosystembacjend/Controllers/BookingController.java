package com.example.demosystembacjend.Controllers;

import com.example.demosystembacjend.Entities.Booking;
import com.example.demosystembacjend.Entities.AccType;
import com.example.demosystembacjend.Entities.PricePerType;
import com.example.demosystembacjend.Entities.PricePeriod;
import com.example.demosystembacjend.Services.BookingService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
public class BookingController {



    private final BookingService service;
    Booking bookingForPriceCalculation;

    @Autowired
    public BookingController(BookingService service) {
        this.service = service;
    }


    @GetMapping("/rezerwacje")
    public List<Booking> getBookings(){
return service.getBookings();
    }

    @GetMapping("/rezerwacje2")
    public void getBookings2(){
        int a = 0;
        a = a/a;
    }
   @PostMapping("/dodajRezerwacje")
    public void savaBooking(@RequestBody Booking booking){
        System.out.println(booking.getName());
        System.out.println(booking.getArrivalDate());
        System.out.println(booking.getDepartureDate());
        System.out.println(booking.getAccTypeFK());
   service.addNewBooking(booking);
    }
    @GetMapping("/getBookingByArrivalDate")
    public List<Booking> findBookingsByArrivalDate(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return service.getBookingByArrivalDate(localDate);
    }


    @GetMapping("/getBookingByDepartureDate")
    public List<Booking> findBookingsByDepartureDate(@RequestParam("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return service.getBookingByDepartureDate(localDate);

  }
   @GetMapping("/types")
    public List<AccType> getAccTypes(){
        return service.getAccTypes();
    }

    @GetMapping("/periods")
    public List<PricePeriod> getPricePeriods(){
        return service.getPricePeriod();
    }

    @GetMapping("/getPrices")
    public List<PricePerType> getPrices(){
        return service.getPrices();
    }

    @PostMapping("/updatePriceList")
    public void updatePriceList(@RequestBody List<PricePerType> pricePerType){
        service.updateObjects(pricePerType);
    }

    @PostMapping("/sendPriceData")
    public void sendPriceList(@RequestBody Booking booking){
        bookingForPriceCalculation = booking;
    }


    @GetMapping("/getPrice")
    public Double getPrice() {

        return service.calculatePrice(bookingForPriceCalculation);
    }

    @GetMapping("/getBookingById")
    public Booking findBookingById(@RequestParam("id") Integer id){
        System.out.println("Tutu");
        return service.findBookingById(id);

    }





}



