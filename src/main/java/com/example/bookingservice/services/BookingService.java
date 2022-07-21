package com.example.bookingservice.services;

import com.example.bookingservice.models.Booking;
import com.example.bookingservice.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Booking findOne(int id) {
        Optional<Booking> foundBooking = bookingRepository.findById(id);
        return foundBooking.orElse(null);
    }

    @Transactional
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Transactional
    public void update(int id, Booking updatedBooking) {
        updatedBooking.setId(id);
        bookingRepository.save(updatedBooking);
    }

    @Transactional
    public void delete(int id) {
        bookingRepository.deleteById(id);
    }
}
