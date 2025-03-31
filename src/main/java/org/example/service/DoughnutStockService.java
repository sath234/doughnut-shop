package org.example.service;

import org.example.model.Day;
import org.example.model.DoughnutType;

import java.util.Map;

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

    /**
     * Calculates the total sugar (in grams) needed for all doughnuts produced on a specific day.
     *
     * @param doughnutAmount a map where each key is a doughnut type, and each value the amount of doughnuts being purchased
     * @return the total amount of sugar needed for that day
     */
    double calculateCostOfDoughnuts(Map<DoughnutType, Integer> doughnutAmount);

    /**
     * Retrieves the available quantity of a specific type of doughnut for a given day.
     *
     * @param doughnutType the type of doughnut
     * @param day          the day to check availability
     * @return the number of doughnuts available
     */
    int getDoughnutsForDay(DoughnutType doughnutType, Day day);

    /**
     * Adds a specified quantity of a particular doughnut type to the stock for a given day.
     *
     * @param doughnutType the type of doughnut to add
     * @param day          the day to add the stock to
     * @param amount       the quantity of doughnuts to add
     */
    void addDoughnutStock(DoughnutType doughnutType, Day day, int amount);

    /**
     * Removes a specified quantity of a particular doughnut type from the stock for a given day.
     *
     * @param doughnutType the type of doughnut to remove
     * @param day          the day to remove the stock from
     * @param amount       the quantity of doughnuts to remove
     */
    void removeDoughnutStock(DoughnutType doughnutType, Day day, int amount);

    /**
     * Adds multiple quantities of various doughnut types to their respective daily schedules.
     *
     * @param bulkUpdates a map where each key is a doughnut type, and each value is another map of day-to-amount pairs
     *                    specifying how many doughnuts to add to each day for that type
     */
    void addBulkStock(Map<DoughnutType, Map<Day, Integer>> bulkUpdates);

    /**
     * Removes multiple quantities of various doughnut types from their respective daily schedules.
     *
     * @param bulkRemovals a map where each key is a doughnut type, and each value is another map of day-to-amount pairs
     *                     specifying how many doughnuts to remove from each day for that type
     */
    void removeBulkStock(Map<DoughnutType, Map<Day, Integer>> bulkRemovals);
}