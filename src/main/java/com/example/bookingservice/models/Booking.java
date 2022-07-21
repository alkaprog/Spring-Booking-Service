package com.example.bookingservice.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "guest_id")
    private int guestId;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "booking_begin")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bookingBegin;

    @Column(name = "booking_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bookingEnd;

    public Booking() {

    }

    public Booking(int guestId, int roomId, Date bookingBegin, Date bookingEnd) {
        this.guestId = guestId;
        this.roomId = roomId;
        this.bookingBegin = bookingBegin;
        this.bookingEnd = bookingEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int personId) {
        this.guestId = personId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getBookingBegin() {
        return bookingBegin;
    }

    public void setBookingBegin(Date begin) {
        this.bookingBegin = begin;
    }

    public Date getBookingEnd() {
        return bookingEnd;
    }

    public void setBookingEnd(Date end) {
        this.bookingEnd = end;
    }
}