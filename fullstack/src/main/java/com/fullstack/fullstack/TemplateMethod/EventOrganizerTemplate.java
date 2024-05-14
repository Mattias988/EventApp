/**
 * @file EventOrganizerTemplate.java
 * @brief This file contains the definition of the EventOrganizerTemplate class, which serves as a template for organizing events.
 */

package com.fullstack.fullstack.TemplateMethod;

import com.fullstack.fullstack.Entity.Event;

/**
 * @class EventOrganizerTemplate
 * @brief This abstract class defines a template for organizing events.
 */
public abstract class EventOrganizerTemplate {

    /**
     * Template method to organize an event.
     * @param event The event to be organized.
     */
    public final void organizeEvent(Event event) {
        validateEventDetails(event);
        prepareEventResources(event);
        finalizeEventSetup(event);
    }

    /**
     * Validates the details of the event.
     * @param event The event to validate.
     */
    protected abstract void validateEventDetails(Event event);

    /**
     * Prepares resources for the event.
     * @param event The event for which resources are prepared.
     */
    protected abstract void prepareEventResources(Event event);

    /**
     * Finalizes the setup for the event.
     * @param event The event for which setup is finalized.
     */
    protected abstract void finalizeEventSetup(Event event);
}
