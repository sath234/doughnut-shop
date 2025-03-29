package org.example.service;

import org.example.model.Day;
import org.example.model.DoughnutType;

public interface DoughnutStockService {
    int calculateFlourForEachDoughnut();
    int calculateTotalSugarForDay(Day day);
    void removeDoughnutProductStock(DoughnutType doughnutType, Day day, int amount);
}
