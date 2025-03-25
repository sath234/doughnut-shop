package org.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RingofFire extends Doughnut{
    public RingofFire(double price, int amount) {
        super(price, DoughnutType.RING_OF_FIRE, 30, 15, amount);
        this.price = price;
        this.amount = amount;
    }
}
