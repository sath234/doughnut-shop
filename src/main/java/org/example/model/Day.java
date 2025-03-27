package org.example.model;

import lombok.Getter;

@Getter
public enum Day {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String dayName;

    Day(String dayName) {
        this.dayName = dayName;
    }

    public String getDayName() {
        return dayName;
    }
}

