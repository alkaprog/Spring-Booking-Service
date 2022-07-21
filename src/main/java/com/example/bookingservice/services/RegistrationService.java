package com.example.bookingservice.services;

import com.example.bookingservice.models.Guest;
import com.example.bookingservice.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional
    public void register(Guest guest) {
        String encodedPassword = passwordEncoder.encode(guest.getPassword());
        guest.setPassword(encodedPassword);
        guest.setRole("ROLE_USER");
        guestRepository.save(guest);
    }
}
