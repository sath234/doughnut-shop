package service;

import org.example.model.*;
import org.example.service.DoughnutStockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoughnutTestStockServiceImplTest {
    private DoughnutStockServiceImpl doughnutProductStockCalculatorService;
    private List<Doughnut> doughnuts;

    @BeforeEach
    void setUp() {
        doughnuts = new ArrayList<>(List.of(
                new TheOneTrueRing(),
                new DohNuts(),
                new RingOfFire()
        ));
    }

    @Test
    void givenDoughnuts_whenCalculatingTotalFlourForEachDoughnut_thenReturnCorrectTotal() {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);
        int totalFlour = doughnutProductStockCalculatorService.calculateFlourForEachDoughnut();
        assertEquals(120, totalFlour);
    }

    private static Stream<Arguments> dayAndExpectedSugarParameters() {
        return Stream.of(
                Arguments.of(Day.SATURDAY, 1350),
                Arguments.of(Day.SUNDAY, 775)
        );
    }

    @ParameterizedTest
    @MethodSource("dayAndExpectedSugarParameters")
    void givenDay_whenCalculatingTotalSugar_thenReturnExpectedAmount(Day day, int expectedFlour) {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);
        int totalFlour = doughnutProductStockCalculatorService.calculateTotalSugarForDay(day);

        assertEquals(expectedFlour, totalFlour);
    }

    private static Stream<Arguments> invalidDayAndExceptionMessageParameters() {
        return Stream.of(
                Arguments.of(null, "Day is null"),
                Arguments.of(Day.MONDAY, "Invalid day: no doughnut scheduled on Monday"),
                Arguments.of(Day.TUESDAY, "Invalid day: no doughnut scheduled on Tuesday"),
                Arguments.of(Day.WEDNESDAY, "Invalid day: no doughnut scheduled on Wednesday"),
                Arguments.of(Day.THURSDAY, "Invalid day: no doughnut scheduled on Thursday"),
                Arguments.of(Day.FRIDAY, "Invalid day: no doughnut scheduled on Friday")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDayAndExceptionMessageParameters")
    void givenInvalidOrNullDay_whenCalculatingTotalSugar_thenThrowIllegalArgumentException(Day day, String expectedMessage) {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> doughnutProductStockCalculatorService.calculateTotalSugarForDay(day)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }
}
