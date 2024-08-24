package com.example.demosystembacjend.Services;

import com.example.demosystembacjend.Entities.AccType;
import com.example.demosystembacjend.Entities.Booking;
import com.example.demosystembacjend.Entities.PricePerType;
import com.example.demosystembacjend.Entities.PricePeriod;
import com.example.demosystembacjend.Repositories.AccTypeRepository;
import com.example.demosystembacjend.Repositories.BookingRepository;
import com.example.demosystembacjend.Repositories.PricePerTypeRepository;
import com.example.demosystembacjend.Repositories.PricePeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {


    private final BookingRepository bookingRepository;
    private final AccTypeRepository accTypeRepository;
    private final PricePeriodRepository pricePeriodRepository;

  private final PricePerTypeRepository pricePerTypeRepository;


 @Autowired
 public BookingService(BookingRepository bookingRepository, AccTypeRepository accTypeRepository, PricePeriodRepository pricePeriodRepository, PricePerTypeRepository pricePerTypeRepository) {
        this.bookingRepository = bookingRepository;
     this.accTypeRepository = accTypeRepository;
     this.pricePeriodRepository = pricePeriodRepository;
     this.pricePerTypeRepository = pricePerTypeRepository;
 }


    @GetMapping("/rezerwacje")
    public List<Booking> getBookings(){
        return bookingRepository.findAll();
    }


    @PostMapping("/dodajRezerwacje")
    public void addNewBooking(Booking booking) {
        bookingRepository.save(booking);
    }

   @GetMapping("/types")
    public List<AccType> getAccTypes() {return accTypeRepository.findAll();
    }
    @GetMapping("/periods")
    public List<PricePeriod> getPricePeriod(){
     return pricePeriodRepository.findAll();
    }

    @GetMapping("/getPrices")
    public List<PricePerType> getPrices(){
     return pricePerTypeRepository.findAll();
    }

public List<Booking> getBookingByArrivalDate(LocalDate arrivalDate)
{
     return bookingRepository.findBookingByArrivalDate(arrivalDate);
 }

    public List<Booking> getBookingByDepartureDate(LocalDate departureDate) {
        return bookingRepository.findBookingByDepartureDate(departureDate);
    }


    @PostMapping("/updatePriceList")
    public void updateObjects(List<PricePerType> list) {
        for (PricePerType ppt : list) {
            Optional<PricePerType> existingObjectOptional = pricePerTypeRepository.findById(ppt.getId());
            if (existingObjectOptional.isPresent()) {
                PricePerType existingObject = existingObjectOptional.get();
                existingObject.setPrice(ppt.getPrice());
                pricePerTypeRepository.save(existingObject);
            }
        }
    }

    public Double calculatePrice(Booking booking){
        LocalDate start = booking.getArrivalDate();
        System.out.println("start = " + start);
        LocalDate end = booking.getDepartureDate();
        System.out.println("end = " + end);
        AccType accType = booking.getAccTypeFK();
        System.out.println("accTYpe in calculate Price method: " + accType);
        List<LocalDate> datesInBooking = start.datesUntil(end.plusDays(0))
                .collect(Collectors.toList());
        for(LocalDate d : datesInBooking) {
            System.out.println(d.toString());
        }
     Double price = 0.0;

        Map<PricePeriod, Integer> daysForPeriod = new HashMap<>();
        for(LocalDate d : datesInBooking){
            price += getPricePerNight(d, accType);

        }
     return price;
    }
    public Double getPricePerNight(LocalDate date, AccType accType){
     List<PricePeriod> pricePeriodList = pricePeriodRepository.findAll();
     for(PricePeriod p : pricePeriodList){
         System.out.println(p.getName());
     }
     double price= 0.0;
     for(PricePeriod pricePeriod : pricePeriodList){
         if(date.isAfter(pricePeriod.getStartTime()) && date.isBefore(pricePeriod.getEndTime())){
             System.out.println("here " + pricePeriod.getName());
             System.out.println(accType.getName());
             price = pricePerTypeRepository.findByPricePeriodAndAccType(pricePeriod, accType).getPrice();
         }

     }
     return price;
    }


    public Booking findBookingById(Integer id) {
     return bookingRepository.findBookingById(id);
    }
}
