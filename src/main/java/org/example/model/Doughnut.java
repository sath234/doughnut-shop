package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Doughnut {
    protected double price;
    protected DoughnutType type;
    protected int flour;
    protected int sugar;
    protected Map<Day, Integer> schedule;
}
