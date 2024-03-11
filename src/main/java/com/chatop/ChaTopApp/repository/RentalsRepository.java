package com.chatop.ChaTopApp.repository;

import com.chatop.ChaTopApp.model.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, Integer> {
}
