package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DoughnutProduct {
    private DoughnutType type;
    private double price;
}
