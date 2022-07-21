package com.example.bookingservice.services;

import com.example.bookingservice.models.Room;
import com.example.bookingservice.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findOne(int id) {
        Optional<Room> foundRoom = roomRepository.findById(id);
        return foundRoom.orElse(null);
    }

    @Transactional
    public void save(Room room) {
        roomRepository.save(room);
    }

    @Transactional
    public void update(int id, Room updatedRoom) {
        updatedRoom.setId(id);
        roomRepository.save(updatedRoom);
    }

    @Transactional
    public void delete(int id) {
        roomRepository.deleteById(id);
    }
}
