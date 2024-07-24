package com.example.demosystembacjend.Repositories;

import com.example.demosystembacjend.Entities.AccType;
import com.example.demosystembacjend.Entities.PricePerType;
import com.example.demosystembacjend.Entities.PricePeriod;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PricePerTypeRepository extends JpaRepository<PricePerType, Integer> {


    PricePerType findByPricePeriodAndAccType(PricePeriod pricePeriod, AccType accType);

    }


