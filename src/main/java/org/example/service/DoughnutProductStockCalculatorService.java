package org.example.service;

import org.example.model.Day;
import org.example.model.DoughnutType;

public interface DoughnutProductStockCalculatorService {
    int calculateFlourForEachDoughnut();
    int calculateTotalSugarForDay(Day day);
    void removeDoughnutProductStock(DoughnutType doughnutType, Day day, int amount);
}
