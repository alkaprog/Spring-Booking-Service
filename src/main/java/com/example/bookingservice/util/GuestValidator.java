package com.example.bookingservice.util;


import com.example.bookingservice.models.Guest;
import com.example.bookingservice.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GuestValidator implements Validator {
    private GuestService guestService;

    @Autowired
    public GuestValidator(GuestService guestService) {
        this.guestService = guestService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Guest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Guest guest = (Guest) target;
        if (guestService.guestExist(guest.getEmail())) {
            errors.rejectValue("email", "", "Человек с таким адресом уже зарегистрирован");
        }
    }
}
