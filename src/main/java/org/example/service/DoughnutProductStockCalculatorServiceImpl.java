package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Doughnut;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class DoughnutProductStockCalculatorServiceImpl implements DoughnutProductStockCalculatorService {
    Map<String, List<Doughnut>> weekSchedule;
    List<Doughnut> productList;

    @Override
    public int calculateFlourForEachDoughnut() {
        return productList.stream()
                .mapToInt(Doughnut::getFlour)
                .sum();
    }

    @Override
    public int calculateTotalSugarForDay(String day) {
        return weekSchedule.get(day).stream()
                .mapToInt(s -> s.getSugar() * s.getAmount())
                .sum();
    }
}
