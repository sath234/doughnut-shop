package org.example.service;

import org.example.model.Day;

/**
 * Service interface for managing doughnut stock and calculating ingredient requirements.
 * <p>
 * Provides methods for computing flour and sugar needs, as well as managing
 * daily stock availability for different types of doughnuts.
 * </p>
 */
public interface DoughnutStockService {

    /**
     * Calculates the amount of flour (in grams) required to make a single doughnut.
     *
     * @return the amount of flour needed per doughnut
     */
    int calculateFlourForEachDoughnut();

    /**
     * Calculates the total sugar (in grams) needed for all doughnuts produced on a specific day.
     *
     * @param day the day for which the sugar requirement should be calculated
     * @return the total amount of sugar needed for that day
     */
    int calculateTotalSugarForDay(Day day);
}