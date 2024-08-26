package com.architecture.hexagonal.ch2.framework.adapters.output.file.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public enum RouterTypeJson {
    EDGE,
    CORE;
}