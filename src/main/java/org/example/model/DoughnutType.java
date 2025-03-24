package org.example.model;

import lombok.Getter;

@Getter
public enum DoughnutType {
    THE_ONE_TRUE_RING("The One True Ring", 60, 30),
    DOH_NUTS("Doh Nuts", 30, 20),
    RING_OF_FIRE("Ring of Fire", 30, 15),;

    private String doughnutName;
    private int flourRequired;
    private int sugarRequired;

    DoughnutType(String doughnutName, int flourRequired, int sugarRequired) {
        this.doughnutName = doughnutName;
        this.flourRequired = flourRequired;
        this.sugarRequired = sugarRequired;
    }
}
