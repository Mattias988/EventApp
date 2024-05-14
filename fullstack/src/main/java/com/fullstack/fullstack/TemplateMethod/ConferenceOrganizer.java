/**
 * @file ConferenceOrganizer.java
 * @brief This file contains the implementation of the ConferenceOrganizer class, which extends the EventOrganizerTemplate class.
 */

package com.fullstack.fullstack.TemplateMethod;

import com.fullstack.fullstack.Entity.Event;
import com.fullstack.fullstack.Repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @class ConferenceOrganizer
 * @brief This class implements the ConferenceOrganizer, which extends the EventOrganizerTemplate and provides specific implementations for event organization.
 */
@Service
public class ConferenceOrganizer extends EventOrganizerTemplate {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Validates event details.
     * @param event The event to validate.
     * @throws IllegalArgumentException Thrown if event details are invalid.
     */
    @Override
    protected void validateEventDetails(Event event) {
        if (event.getName() == null || event.getName().isEmpty()) {
            throw new IllegalArgumentException("Nazwa wydarzenia jest wymagana");
        }
        if (event.getDate() == null) {
            throw new IllegalArgumentException("Data wydarzenia jest wymagana");
        }
    }

    /**
     * Prepares resources for the event.
     * @param event The event for which resources are prepared.
     */
    @Override
    protected void prepareEventResources(Event event) {
        System.out.println("Rezerwacja sali konferencyjnej i sprzętu audio-video dla " + event.getName());
        System.out.println("Potwierdzenie terminów z prelegentami");
        System.out.println("Zamówienie cateringu na " + event.getDate());
    }

    /**
     * Finalizes the setup for the event.
     * @param event The event for which setup is finalized.
     */
    @Override
    protected void finalizeEventSetup(Event event) {
        System.out.println("Wysyłanie potwierdzeń do uczestników konferencji " + event.getName());

    }
}
