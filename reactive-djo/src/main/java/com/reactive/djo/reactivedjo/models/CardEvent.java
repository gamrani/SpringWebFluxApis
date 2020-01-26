package com.reactive.djo.reactivedjo.models;

import java.util.Objects;

public class CardEvent {

    private Long eventId;
    private String eventType;

    public CardEvent(Long eventId, String eventType) {
        this.eventId = eventId;
        this.eventType = eventType;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardEvent)) return false;
        CardEvent cardEvent = (CardEvent) o;
        return getEventId().equals(cardEvent.getEventId()) &&
                getEventType().equals(cardEvent.getEventType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEventId(), getEventType());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
