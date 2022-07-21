package com.example.bookingservice.services;

import com.example.bookingservice.models.Guest;
import com.example.bookingservice.repositories.GuestRepository;
import com.example.bookingservice.security.GuestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class GuestService implements UserDetailsService {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest findOne(int id) {
        Optional<Guest> foundGuest = guestRepository.findById(id);
        return foundGuest.orElse(null);
    }

    @Transactional
    public void save(Guest guest) {
        guestRepository.save(guest);
    }

    @Transactional
    public void update(int id, Guest updatedGuest) {
        updatedGuest.setId(id);
        guestRepository.save(updatedGuest);
    }

    @Transactional
    public void delete(int id) {
        guestRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Guest> guest = guestRepository.findByEmail(username);
        if (guest.isEmpty()) {
            throw new UsernameNotFoundException("User not Found");
        }
        return new GuestDetails(guest.get());
    }
}
