package com.architecture.hexagonal.ch2.application.port.output;

import com.architecture.hexagonal.ch2.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRouters();
}
