package com.example.bookingservice.controllers;

import com.example.bookingservice.models.Guest;
import com.example.bookingservice.services.RegistrationService;
import com.example.bookingservice.util.GuestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private GuestValidator guestValidator;
    private RegistrationService registrationService;

    @Autowired
    public AuthController(GuestValidator guestValidator, RegistrationService registrationService) {
        this.guestValidator = guestValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("guest") Guest guest) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid Guest guest, BindingResult bindingResult) {
        guestValidator.validate(guest, bindingResult);
        System.out.println("here1");
        if (bindingResult.hasErrors()) {
            System.out.println("here2");
            return "/auth/registration";
        }
        System.out.println("herer");
        registrationService.register(guest);
        return "redirect:/auth/login";
    }
}
