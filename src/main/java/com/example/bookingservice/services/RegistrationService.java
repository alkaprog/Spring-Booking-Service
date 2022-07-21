package com.example.bookingservice.services;

import com.example.bookingservice.models.Guest;
import com.example.bookingservice.repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final GuestRepository guestRepository;

    @Autowired
    public RegistrationService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Transactional
    public void register(Guest guest){
        System.out.println(guest);
        guestRepository.save(guest);
    }
}
