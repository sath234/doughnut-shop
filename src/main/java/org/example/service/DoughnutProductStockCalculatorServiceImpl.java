package org.example.service;

import org.example.model.Doughnut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoughnutProductStockCalculatorServiceImpl implements DoughnutProductStockCalculatorService {
    Map<String, List<Doughnut>> weekSchdule = new HashMap<>();
    List<Doughnut> productList = new ArrayList<>();

    @Override
    public int calculateFlourForEachDoughnut() {
        return productList.stream()
                .mapToInt(Doughnut::getFlour)
                .sum();
    }

    @Override
    public int calculateTotalSugarForDay(String day) {
        return weekSchdule.get(day).stream()
                .mapToInt(Doughnut::getSugar)
                .sum();
    }
}
