package com.example.bookingservice.controllers;

import com.example.bookingservice.models.Booking;
import com.example.bookingservice.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("bookings", bookingService.findAll());
        return "booking/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("booking", bookingService.findOne(id));
        return "booking/show";
    }

    @GetMapping("/new")
    public String newBooking(@ModelAttribute("booking") Booking booking) {
        return "booking/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("booking") @Valid Booking booking, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "booking/new";

        bookingService.save(booking);
        return "redirect:/booking";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("booking", bookingService.findOne(id));
        return "booking/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("booking") @Valid Booking booking, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "booking/edit";

        bookingService.update(id, booking);
        return "redirect:/booking";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookingService.delete(id);
        return "redirect:/booking";
    }
}
