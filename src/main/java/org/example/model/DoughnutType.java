package org.example.model;

import lombok.Getter;

@Getter
public enum DoughnutType {
    THE_ONE_TRUE_RING("The One True Ring"),
    DOH_NUTS("Doh Nuts"),
    RING_OF_FIRE("Ring of Fire");

    private final String displayName;

    DoughnutType(String displayName) {
        this.displayName = displayName;
    }
}
