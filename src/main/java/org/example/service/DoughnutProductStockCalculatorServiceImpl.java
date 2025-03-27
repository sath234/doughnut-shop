package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Day;
import org.example.model.Doughnut;
import org.example.model.DoughnutType;

import java.util.List;

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

        doughnuts.stream()
                .filter(d -> d.getSchedule().containsKey(day))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid day: no doughnut scheduled on " + day.getDayName()));


        return doughnuts.stream()
                .mapToInt(s -> s.getSchedule().get(day) * s.getSugar())
                .sum();
    }

    @Override
    public void removeDoughnutProductStock(DoughnutType doughnutType, Day day, int amount) {
        if (doughnutType == null) throw new IllegalArgumentException("doughnut type is null");
        if (day == null) throw new IllegalArgumentException("day is null");
        if (amount <= 0) throw new IllegalArgumentException("amount must be greater than 0");

        Doughnut doughnut = doughnuts.stream()
                .filter(d -> d.getType() == doughnutType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid doughnut type: " + doughnutType.getDisplayName()));

        Integer currentStock = doughnut.getSchedule().get(day);

        if (currentStock < amount) {
            throw new IllegalArgumentException("Doughnut doesn't have sufficient stock on " + day.getDayName() + " for doughnut type " + doughnutType.getDisplayName());
        }

        doughnut.getSchedule().put(day, currentStock - amount);
    }
}
