package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class Doughnut {
    protected double price;
    protected DoughnutType type;
    protected int flour;
    protected int sugar;
    protected int amount;
}
