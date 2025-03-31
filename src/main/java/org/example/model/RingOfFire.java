package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RingOfFire extends Doughnut {
    public RingOfFire(double price, Map<Day, Integer> schedule) {
        super(price, DoughnutType.RING_OF_FIRE, 30, 15, schedule);
        this.price = price;
        this.schedule = schedule;
    }
}
