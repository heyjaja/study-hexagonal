package com.architecture.hexagonal.ch2.domain.entity;

import com.architecture.hexagonal.ch2.domain.vo.Activity;
import com.architecture.hexagonal.ch2.domain.vo.EventId;

import java.time.OffsetDateTime;

public class Event implements Comparable<Event> {
    private EventId id;
    private OffsetDateTime timestamp;
    private String protocol;
    private Activity activity;

    @Override
    public int compareTo(Event o) {
        return 0;
    }
}
