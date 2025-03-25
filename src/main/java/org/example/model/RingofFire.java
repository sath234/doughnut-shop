package org.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RingofFire extends Doughnut{
    public RingofFire(double price) {
        super(price, DoughnutType.RING_OF_FIRE, 30, 15);
        this.price = price;
    }
}
