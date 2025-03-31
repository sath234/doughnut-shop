package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Day;
import org.example.model.Doughnut;
import org.example.model.DoughnutType;

import java.util.List;
import java.util.Map;

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
                .mapToInt(s -> s.getSchedule().get(day) * s.getSugar())
                .sum();
    }

    @Override
    public int avaliableDoughnutsForDay(DoughnutType doughnutType, Day day) {
        validateNotNull(day, "Day");

        // Get the doughnut matching the type and return how many are scheduled for the day (defaulting to 0 if none)
        Doughnut doughnut = findDoughnut(doughnutType);
        return doughnut.getSchedule().getOrDefault(day, 0);
    }

    @Override
    public void addDoughnutStock(DoughnutType doughnutType, Day day, int amount) {
        validateNotNull(doughnutType, "doughnutType");
        validateNotNull(day, "Day");
        validateAmount(amount);

        // Find the doughnut and update the stock for the specified day
        Doughnut doughnut = findDoughnut(doughnutType);
        doughnut.getSchedule().compute(day, (k, currentStock) -> currentStock + amount);
    }

    @Override
    public void removeDoughnutStock(DoughnutType doughnutType, Day day, int amount) {
        validateNotNull(doughnutType, "doughnutType");
        validateNotNull(day, "Day");
        validateAmount(amount);

        Doughnut doughnut = findDoughnut(doughnutType);
        Integer currentStock = doughnut.getSchedule().get(day);

        // Make sure there's enough stock to remove
        if (currentStock < amount) {
            throw new IllegalArgumentException("Doughnut doesn't have sufficient stock on " + day.getDayName() + " for doughnut type " + doughnutType.getDisplayName());
        }

        // Subtract the amount from the existing stock
        doughnut.getSchedule().put(day, currentStock - amount);
    }

    @Override
    public void addBulkStock(Map<DoughnutType, Map<Day, Integer>> bulkUpdates) {
        // For each doughnut type provided in the update map
        bulkUpdates.forEach((doughnutType, dayMap) -> {
            validateNotNull(doughnutType, "doughnutType");
            Doughnut doughnut = findDoughnut(doughnutType);

            // For each day/amount pair, validate and merge the amount into the current schedule
            dayMap.forEach((day, amount) -> {
                validateNotNull(day, "Day");
                validateAmount(amount);

                // Increase the scheduled quantity for the day by the specified amount
                doughnut.getSchedule().merge(day, amount, Integer::sum);
            });
        });
    }

    @Override
    public void removeBulkStock(Map<DoughnutType, Map<Day, Integer>> bulkRemovals) {
        // For each doughnut type provided in the removal map
        bulkRemovals.forEach((doughnutType, dayMap) -> {
            validateNotNull(doughnutType, "doughnutType");
            Doughnut doughnut = findDoughnut(doughnutType);

            // For each day/amount pair, validate and subtract the amount from the schedule
            dayMap.forEach((day, amount) -> {
                validateNotNull(day, "Day");
                validateAmount(amount);

                Integer currentStock = doughnut.getSchedule().get(day);
                if (currentStock == null || currentStock < amount) {
                    throw new IllegalArgumentException("Doughnut doesn't have sufficient stock on " + day.getDayName() + " for doughnut type " + doughnutType.getDisplayName());
                }

                // Decrease the scheduled quantity for the day by the specified amount
                doughnut.getSchedule().put(day, currentStock - amount);
            });
        });
    }

    // ----------- Helper Methods -----------

    // Throws an exception if the object is null
    private <T> void validateNotNull(T obj, String name) {
        if (obj == null) throw new IllegalArgumentException(name + " is null");
    }

    // Ensures amount is positive
    private void validateAmount(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount must be greater than 0");
    }

    // Makes sure there's at least one doughnut scheduled for the given day
    private void ensureAtLeastOneDoughnutScheduled(Day day) {
        boolean found = doughnuts.stream().anyMatch(d -> d.getSchedule().containsKey(day));
        if (!found) throw new IllegalArgumentException("Invalid day: no doughnut scheduled on " + day.getDayName());
    }

    // Finds a doughnut by its type or throws an exception if it's missing
    private Doughnut findDoughnut(DoughnutType type) {
        return doughnuts.stream()
                .filter(d -> d.getType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid doughnut type: " + type.getDisplayName()));
    }
}
