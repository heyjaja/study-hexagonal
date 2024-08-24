package com.architecture.hexagonal.ch2.domain.policy;

import com.architecture.hexagonal.ch2.domain.entity.Event;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public interface EventParser {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.of("UTC"));

    Event parseEvent(String event);
}
