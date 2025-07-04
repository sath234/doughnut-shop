package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TheOneTrueRing extends Doughnut{
    public TheOneTrueRing() {
        super("The One True Ring",3.00, 60, 30, Map.of(Day.SATURDAY, 20, Day.SUNDAY, 10));
    }
}
