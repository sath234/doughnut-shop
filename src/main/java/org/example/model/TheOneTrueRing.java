package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TheOneTrueRing extends Doughnut{
    public TheOneTrueRing(double price, int amount) {
        super(price, DoughnutType.THE_ONE_TRUE_RING, 60, 30, amount);
        this.price = price;
        this.amount = amount;
    }
}
