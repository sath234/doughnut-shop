package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Day;
import org.example.model.Doughnut;
import org.example.model.DoughnutType;

import java.util.List;

@AllArgsConstructor
public class DoughnutStockServiceImpl implements DoughnutStockService {
    private List<Doughnut> doughnuts;

    @Override
    public int calculateFlourForEachDoughnut() {
        return doughnuts.stream()
                .mapToInt(Doughnut::getFlour)
                .sum();
    }

    @Override
    public int calculateTotalSugarForDay(Day day) {
        validateNotNull(day, "Day");
        ensureAtLeastOneDoughnutScheduled(day);
        return doughnuts.stream()
                .mapToInt(s -> s.getSchedule().get(day) * s.getSugar())
                .sum();
    }

    @Override
    public void removeDoughnutProductStock(DoughnutType doughnutType, Day day, int amount) {
        validateNotNull(doughnutType, "doughnutType");
        validateNotNull(day, "Day");
        validateAmount(amount);

        Doughnut doughnut = findDoughnut(doughnutType);
        Integer currentStock = doughnut.getSchedule().get(day);

        if (currentStock == null || currentStock < amount) {
            throw new IllegalArgumentException("Doughnut doesn't have sufficient stock on " + day.getDayName() + " for doughnut type " + doughnutType.getDisplayName());
        }

        doughnut.getSchedule().put(day, currentStock - amount);
    }

    // Validation helpers
    private <T> void validateNotNull(T obj, String name) {
        if (obj == null) throw new IllegalArgumentException(name + " is null");
    }

    private void validateAmount(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be greater than 0");
    }

    private void ensureAtLeastOneDoughnutScheduled(Day day) {
        boolean found = doughnuts.stream().anyMatch(d -> d.getSchedule().containsKey(day));
        if (!found) throw new IllegalArgumentException("Invalid day: no doughnut scheduled on " + day.getDayName());
    }

    private Doughnut findDoughnut(DoughnutType type) {
        return doughnuts.stream()
                .filter(d -> d.getType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid doughnut type: " + type.getDisplayName()));
    }
}
