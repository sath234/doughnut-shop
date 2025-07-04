package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DohNuts extends Doughnut {
    public DohNuts() {
        super("Doh Nuts", 2.50, 30, 20, Map.of(Day.SATURDAY, 30, Day.SUNDAY, 20));
    }
}
