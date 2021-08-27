package com.example.reactive_demo.domain;

import lombok.Getter;

@Getter
public class Temperature {
    private final double value;

    public Temperature(double value) {
        this.value = value;
    }
}
