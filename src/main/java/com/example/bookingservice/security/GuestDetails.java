package com.example.bookingservice.security;

import com.example.bookingservice.models.Guest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class GuestDetails implements UserDetails {
    private final Guest guest;


    public GuestDetails(Guest guest) {
        this.guest = guest;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(guest.getRole()));
    }

    @Override
    public String getPassword() {
        return guest.getPassword();
    }

    @Override
    public String getUsername() {
        return guest.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Guest getGuest() {
        return this.guest;
    }

    @Override
    public String toString() {
        return "GuestDetails{" +
                "guest=" + guest.toString() +
                '}';
    }
}
