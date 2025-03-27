package service;

import org.example.model.*;
import org.example.service.DoughnutProductStockCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoughnutProductStockCalculatorServiceImplTest {
    private DoughnutProductStockCalculatorServiceImpl doughnutProductStockCalculatorService;
    private List<Doughnut> doughnuts;

    @BeforeEach
    void setUp() {
        EnumMap<Day, Integer> theOneTrueRingSchedule = new EnumMap<>(Day.class);
        theOneTrueRingSchedule.put(Day.SATURDAY, 20);
        theOneTrueRingSchedule.put(Day.SUNDAY, 10);

        EnumMap<Day, Integer> dohNutsSchedule = new EnumMap<>(Day.class);
        dohNutsSchedule.put(Day.SATURDAY, 30);
        dohNutsSchedule.put(Day.SUNDAY, 20);

        EnumMap<Day, Integer> ringOfFireSchedule = new EnumMap<>(Day.class);
        ringOfFireSchedule.put(Day.SATURDAY, 10);
        ringOfFireSchedule.put(Day.SUNDAY, 5);

        doughnuts = new ArrayList<>(List.of(
                new TheOneTrueRing(3.00, theOneTrueRingSchedule),
                new DohNuts(2.50, dohNutsSchedule),
                new RingOfFire(2.50, ringOfFireSchedule)
        ));
    }

    @Test
    void testTotalFlourForEachDoughnutType() {
        doughnutProductStockCalculatorService = new DoughnutProductStockCalculatorServiceImpl(doughnuts);
        int totalFlour = doughnutProductStockCalculatorService.calculateFlourForEachDoughnut();
        assertEquals(120, totalFlour);
    }

    private static Stream<Arguments> weekScheduleProvider() {
        return Stream.of(
                Arguments.of(Day.SATURDAY, 1350),
                Arguments.of(Day.SUNDAY, 775)
        );
    }

    @ParameterizedTest
    @MethodSource("weekScheduleProvider")
    void testTotalFlourForEachDoughnutType(Day day, int expectedFlour) {
        doughnutProductStockCalculatorService = new DoughnutProductStockCalculatorServiceImpl(doughnuts);
        int totalFlour = doughnutProductStockCalculatorService.calculateTotalSugarForDay(day);

        assertEquals(expectedFlour, totalFlour);
    }

    private static Stream<Arguments> invalidParametersProvider() {
        return Stream.of(
                Arguments.of(null, "day is null"),
                Arguments.of(Day.MONDAY, "Invalid day: no doughnut scheduled on Monday"),
                Arguments.of(Day.TUESDAY, "Invalid day: no doughnut scheduled on Tuesday"),
                Arguments.of( Day.WEDNESDAY, "Invalid day: no doughnut scheduled on Wednesday"),
                Arguments.of(Day.THURSDAY, "Invalid day: no doughnut scheduled on Thursday"),
                Arguments.of(Day.FRIDAY, "Invalid day: no doughnut scheduled on Friday")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidParametersProvider")
    void testExceptionThrownWhenDayIsNull(Day day, String expectedMessage) {
        doughnutProductStockCalculatorService = new DoughnutProductStockCalculatorServiceImpl(doughnuts);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> doughnutProductStockCalculatorService.calculateTotalSugarForDay(day)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }


}
