package com.architecture.hexagonal.ch1.application.port.output;

import com.architecture.hexagonal.ch1.domain.Router;

import java.util.List;

public interface RouterViewOutputPort {
    List<Router> fetchRouters();
}
