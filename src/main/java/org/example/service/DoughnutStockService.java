package org.example.service;

import org.example.model.Day;
import org.example.model.DoughnutType;

public interface DoughnutStockService {
    int calculateFlourForEachDoughnut();
    int calculateTotalSugarForDay(Day day);
    int avaliableDoughnutsForDay(DoughnutType doughnutType, Day day);
    void addDoughnutStock(DoughnutType doughnutType, Day day, int amount);
    void removeDoughnutStock(DoughnutType doughnutType, Day day, int amount);
}
