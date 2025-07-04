package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RingOfFire extends Doughnut {
    public RingOfFire() {
        super("Ring Of Fire", 2.50, 30, 15, Map.of(Day.SATURDAY, 10, Day.SUNDAY, 5));
    }
}
