package org.example.service;

import org.example.model.Day;

public interface DoughnutStockCalculatorService {
    int getTotalFlourForEachDoughnutType();
    int getTotalSugarForDay(Day day);
}
