package com.example.bookingservice.repositories;

import com.example.bookingservice.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    Optional<Guest> findByEmail(String email);
}
