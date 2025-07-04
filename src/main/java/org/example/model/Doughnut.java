package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doughnut {
    private String name;
    private double price;
    private int flour;
    private int sugar;
    private Map<Day, Integer> schedule;
}
