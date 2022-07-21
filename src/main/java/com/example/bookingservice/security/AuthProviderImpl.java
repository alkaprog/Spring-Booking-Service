package com.example.bookingservice.security;

import com.example.bookingservice.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final GuestService guestService;

    @Autowired
    public AuthProviderImpl(GuestService guestService) {
        this.guestService = guestService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails guestDetails = guestService.loadUserByUsername(userName);
        if (!password.equals(guestDetails.getPassword())) {
            throw new BadCredentialsException("Wrong Password");
        }
        return new UsernamePasswordAuthenticationToken(guestDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
