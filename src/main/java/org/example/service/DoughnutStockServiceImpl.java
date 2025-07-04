package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Day;
import org.example.model.Doughnut;

import java.util.List;

@AllArgsConstructor
public class DoughnutStockServiceImpl implements DoughnutStockService {

    private List<Doughnut> doughnuts;

    @Override
    public int calculateFlourForEachDoughnut() {
        // Sums up the amount of flour used by each doughnut type
        return doughnuts.stream()
                .mapToInt(Doughnut::getFlour)
                .sum();
    }

    @Override
    public int calculateTotalSugarForDay(Day day) {
        validateNotNull(day, "Day");
        ensureAtLeastOneDoughnutScheduled(day);

        // For each doughnut, multiply the quantity scheduled for the day by the sugar per unit and sum it all up
        return doughnuts.stream()
                .mapToInt(d -> d.getSchedule().get(day) * d.getSugar())
                .sum();
    }

    // ----------- Helper Methods -----------

    // Throws an exception if the object is null
    private <T> void validateNotNull(T obj, String name) {
        if (obj == null) throw new IllegalArgumentException(name + " is null");
    }

    // Makes sure there's at least one doughnut scheduled for the given day
    private void ensureAtLeastOneDoughnutScheduled(Day day) {
        boolean found = doughnuts.stream().anyMatch(d -> d.getSchedule().containsKey(day));
        if (!found) throw new IllegalArgumentException("Invalid day: no doughnut scheduled on " + day.getDayName());
    }
}
