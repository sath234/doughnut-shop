package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DohNuts extends Doughnut {
    public DohNuts(double price, Map<Day, Integer> schedule) {
        super(price, DoughnutType.DOH_NUTS, 30, 20, schedule);
        this.price = price;
        this.schedule = schedule;
    }
}
