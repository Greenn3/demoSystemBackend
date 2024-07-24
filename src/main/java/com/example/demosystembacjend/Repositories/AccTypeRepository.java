package com.example.demosystembacjend.Repositories;

import com.example.demosystembacjend.Entities.AccType;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccTypeRepository extends JpaRepository<AccType, Integer> {
}
