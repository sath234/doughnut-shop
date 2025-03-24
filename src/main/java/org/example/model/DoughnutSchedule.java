package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumMap;

@Getter
@AllArgsConstructor
public class DoughnutSchedule {
    private DoughnutProduct product;
    private EnumMap<Day, Integer> schedule;

    public int getQuantityForDay(Day day) {
        if (day == null) {
            throw new IllegalArgumentException("Day cannot be null");
        }
        return schedule.getOrDefault(day, 0);
    }
}
