package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Day;
import org.example.model.Doughnut;
import org.example.model.DoughnutType;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class DoughnutProductStockCalculatorServiceImpl implements DoughnutProductStockCalculatorService {
    private List<Doughnut> doughnuts;

    @Override
    public int calculateFlourForEachDoughnut() {
        return doughnuts.stream()
                .mapToInt(Doughnut::getFlour)
                .sum();
    }

    @Override
    public int calculateTotalSugarForDay(Day day) {
        if (day == null) {
            throw new IllegalArgumentException("day is null");
        }

        boolean validDay = doughnuts.stream()
                .anyMatch(d -> d.getSchedule().containsKey(day));

        if (!validDay) {
            throw new IllegalArgumentException("Invalid day: no doughnut scheduled on " + day.getDayName());
        }

        return doughnuts.stream()
                .mapToInt(s -> s.getSchedule().get(day) * s.getSugar())
                .sum();
    }

    @Override
    public void removeDoughnutProductStock(DoughnutType doughnutType, Day day, int amount) {
//        if (day == null ) {
//            throw new IllegalArgumentException("day cannot be null");
//        } else if (!weekSchedule.containsKey(day)) {
//            throw new IllegalArgumentException("Invalid day: " + day);
//        } else if (amount <= 0) {
//            throw new IllegalArgumentException("Invalid amount: " + amount);
//        } else if (amount > weekSchedule.get(day).get(doughnutType).size()) {
//            throw new IllegalArgumentException("Invalid amount: " + amount);
//        }
    }
}
