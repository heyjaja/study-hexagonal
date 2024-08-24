package com.architecture.hexagonal.ch2.domain.entity;

import com.architecture.hexagonal.ch2.domain.policy.RegexEventParser;
import com.architecture.hexagonal.ch2.domain.policy.SplitEventParser;
import com.architecture.hexagonal.ch2.domain.vo.Activity;
import com.architecture.hexagonal.ch2.domain.vo.EventId;
import com.architecture.hexagonal.ch2.domain.vo.ParsePolicyType;
import com.architecture.hexagonal.ch2.domain.vo.Protocol;

import java.time.OffsetDateTime;

public class Event implements Comparable<Event> {
    private OffsetDateTime timestamp;
    private EventId id;
    private Protocol protocol;
    private Activity activity;

    public Event(OffsetDateTime timestamp, EventId id, Protocol protocol, Activity activity) {
        this.timestamp = timestamp;
        this.id = id;
        this.protocol = protocol;
        this.activity = activity;
    }

    public static Event parsedEvent(String unparsedEvent, ParsePolicyType policy) {
        switch (policy) {
            case REGEX:
                return new RegexEventParser().parseEvent(unparsedEvent);
            case SPLIT:
                return new SplitEventParser().parseEvent(unparsedEvent);
            default:
                throw new IllegalArgumentException("Invalid policy");
        }
    }

    @Override
    public int compareTo(Event o) {
        return timestamp.compareTo(o.timestamp);
    }
}
