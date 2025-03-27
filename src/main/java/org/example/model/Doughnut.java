package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.EnumMap;

@Data
@AllArgsConstructor
public class Doughnut {
    protected double price;
    protected DoughnutType type;
    protected int flour;
    protected int sugar;
    protected EnumMap<Day, Integer> schedule;
}
