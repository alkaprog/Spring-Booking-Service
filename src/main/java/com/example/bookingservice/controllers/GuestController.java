package com.example.bookingservice.controllers;

import com.example.bookingservice.models.Guest;
import com.example.bookingservice.security.GuestDetails;
import com.example.bookingservice.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;

    @Autowired
    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("guests", guestService.findAll());
        return "guest/index";
    }

    @GetMapping("/personal")
    public String personal(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GuestDetails guestDetails = (GuestDetails) authentication.getPrincipal();
        System.out.println(guestDetails);
        return "guest/personal";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("guest", guestService.findOne(id));
        return "guest/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("guest") Guest guest) {
        return "guest/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("guest") @Valid Guest guest,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "guest/new";

        guestService.save(guest);
        return "redirect:/guest";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("guest", guestService.findOne(id));
        return "guest/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Guest guest, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "guest/edit";

        guestService.update(id, guest);
        return "redirect:/guest";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        guestService.delete(id);
        return "redirect:/guest";
    }
}
