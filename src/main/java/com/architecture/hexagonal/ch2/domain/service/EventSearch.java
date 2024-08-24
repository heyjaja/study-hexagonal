package com.architecture.hexagonal.ch2.domain.service;

import com.architecture.hexagonal.ch2.domain.entity.Event;
import com.architecture.hexagonal.ch2.domain.vo.ParsePolicyType;

import java.util.ArrayList;
import java.util.List;

public class EventSearch {
    public List<Event> retrieveEvents(List<String> unparsedEvents, ParsePolicyType policyType) {
        var parsedEvents = new ArrayList<Event>();
        unparsedEvents.forEach(event -> parsedEvents.add(Event.parsedEvent(event, policyType)));
        return parsedEvents;
    }
}
