package com.fullstack.fullstack.Controller; /**
 * @file EventController.java
 * @brief This file contains the implementation of the EventController class, which handles HTTP requests related to events.
 */


import com.fullstack.fullstack.DTOs.EventDTO;
import com.fullstack.fullstack.Entity.Equipment;
import com.fullstack.fullstack.Entity.Event;
import com.fullstack.fullstack.Repository.EquipmentRepository;
import com.fullstack.fullstack.Repository.EventRepository;
import com.fullstack.fullstack.TemplateMethod.ConferenceOrganizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @class EventController
 * @brief This class handles HTTP requests related to events.
 */
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EventRepository eventRepository;

    private final ConferenceOrganizer conferenceOrganizer = new ConferenceOrganizer();

    /**
     * Handles the POST request to organize an event.
     * @param eventDTO The event data received from the client.
     * @return ResponseEntity<String> Returns a response entity indicating the status of the event organization.
     */
    @PostMapping("/organize")
    public ResponseEntity<String> organizeEvent(@RequestBody EventDTO eventDTO) {
        try {
            Event event = new Event();
            event.setName(eventDTO.getName());
            event.setDate(eventDTO.getDate());
            Equipment equipment = new Equipment();
            equipment.setName(eventDTO.getName());
            conferenceOrganizer.organizeEvent(event);
            eventRepository.save(event);
            equipmentRepository.save(equipment);
            return ResponseEntity.ok("Event organized successfully and equipment reserved.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during event organization or equipment reservation: " + e.getMessage());
        }
    }

    /**
     * Handles the GET request to retrieve all events.
     * @return List<Event> Returns a list of all events.
     */
    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
