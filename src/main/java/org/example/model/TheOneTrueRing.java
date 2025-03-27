package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;

@Getter
@Setter
public class TheOneTrueRing extends Doughnut{
    public TheOneTrueRing(double price, EnumMap<Day, Integer> schedule) {
        super(price, DoughnutType.THE_ONE_TRUE_RING, 60, 30, schedule);
        this.price = price;
        this.schedule = schedule;
    }
}
