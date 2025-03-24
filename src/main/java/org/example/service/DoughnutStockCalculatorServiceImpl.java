package org.example.service;

import lombok.Data;
import org.example.model.Day;
import org.example.model.DoughnutSchedule;

import java.util.List;

@Data
public class DoughnutStockCalculatorServiceImpl implements DoughnutStockCalculatorService {

    private final List<DoughnutSchedule> schedules;

    public DoughnutStockCalculatorServiceImpl(List<DoughnutSchedule> schedules) {
        if (schedules == null) {
            throw new IllegalArgumentException("Schedules list cannot be null");
        }
        this.schedules = schedules;
    }

    @Override
    public int getTotalFlourForEachDoughnutType() {
        return schedules.stream()
                .map(schedule -> {
                    if (schedule.getProduct() == null || schedule.getProduct().getType() == null) {
                        throw new IllegalStateException("Invalid product or product type in schedule");
                    }
                    return schedule.getProduct().getType().getFlourRequired();
                })
                .distinct()
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public int getTotalSugarForDay(Day day) {
        if (day == null) {
            throw new IllegalArgumentException("Day cannot be null");
        }
        return schedules.stream()
                .mapToInt(schedule -> {
                    if (schedule.getProduct() == null || schedule.getProduct().getType() == null) {
                        throw new IllegalStateException("Invalid product or product type in schedule");
                    }
                    return schedule.getQuantityForDay(day) * schedule.getProduct().getType().getSugarRequired();
                })
                .sum();
    }
    }
