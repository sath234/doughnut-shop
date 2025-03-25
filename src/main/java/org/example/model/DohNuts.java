package org.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DohNuts extends Doughnut {
    public DohNuts(double price) {
        super(price, DoughnutType.DOH_NUTS, 60, 20);
    }
}
