package com.example.bookingservice.models;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "max_guest_count")
    private int maxGuestCount;

    @Column(name = "area")
    private double area;

    @Column(name = "has_air_condition")
    private boolean hasAirCondition;

    @Column(name = "price_per_night")
    private double pricePerNight;

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxGuestCount() {
        return maxGuestCount;
    }

    public void setMaxGuestCount(int maxGuestCount) {
        this.maxGuestCount = maxGuestCount;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isHasAirCondition() {
        return hasAirCondition;
    }

    public void setHasAirCondition(boolean hasAirCondition) {
        this.hasAirCondition = hasAirCondition;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
