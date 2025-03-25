package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DohNuts extends Doughnut {
    public DohNuts(double price, int amount) {
        super(price, DoughnutType.DOH_NUTS, 30, 20, amount);
        this.price = price;
        this.amount = amount;
    }
}
