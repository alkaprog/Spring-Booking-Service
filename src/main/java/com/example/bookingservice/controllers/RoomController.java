package com.example.bookingservice.controllers;

import com.example.bookingservice.models.Booking;
import com.example.bookingservice.models.Room;
import com.example.bookingservice.services.BookingService;
import com.example.bookingservice.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("rooms", roomService.findAll());
        System.out.println("Sout");
        return "room/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("room", roomService.findOne(id));
        return "room/show";
    }

    @GetMapping("/new")
    public String newRoom(@ModelAttribute("room") Room room) {
        return "room/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("room") @Valid Room room, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "room/new";

        roomService.save(room);
        return "redirect:/room";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("room", roomService.findOne(id));
        return "room/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("room") @Valid Room room, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "room/edit";

        roomService.update(id, room);
        return "redirect:/room";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        roomService.delete(id);
        return "redirect:/room";
    }
}
