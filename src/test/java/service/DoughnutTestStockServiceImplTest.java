package service;

import org.example.model.*;
import org.example.service.DoughnutStockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoughnutTestStockServiceImplTest {
    private DoughnutStockServiceImpl doughnutProductStockCalculatorService;
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
    void givenDoughnuts_whenCalculatingTotalFlourForEachDoughnut_thenReturnCorrectTotal() {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);
        int totalFlour = doughnutProductStockCalculatorService.calculateFlourForEachDoughnut();
        assertEquals(120, totalFlour);
    }

    private static Stream<Arguments> dayAndExpectedSugarProvider() {
        return Stream.of(
                Arguments.of(Day.SATURDAY, 1350),
                Arguments.of(Day.SUNDAY, 775)
        );
    }

    @ParameterizedTest
    @MethodSource("dayAndExpectedSugarProvider")
    void givenDay_whenCalculatingTotalSugar_thenReturnExpectedAmount(Day day, int expectedFlour) {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);
        int totalFlour = doughnutProductStockCalculatorService.calculateTotalSugarForDay(day);

        assertEquals(expectedFlour, totalFlour);
    }

    private static Stream<Arguments> invalidDayAndExceptionMessageProvider() {
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
    @MethodSource("invalidDayAndExceptionMessageProvider")
    void givenInvalidOrNullDay_whenCalculatingTotalSugar_thenThrowIllegalArgumentException(Day day, String expectedMessage) {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> doughnutProductStockCalculatorService.calculateTotalSugarForDay(day)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> retrieveDoughnutForDayParameters() {
        return Stream.of(
                Arguments.of(DoughnutType.DOH_NUTS, Day.SATURDAY, 30),
                Arguments.of(DoughnutType.THE_ONE_TRUE_RING, Day.SUNDAY, 10),
                Arguments.of(DoughnutType.RING_OF_FIRE, Day.SATURDAY, 10),
                Arguments.of(DoughnutType.RING_OF_FIRE, Day.MONDAY, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("retrieveDoughnutForDayParameters")
    void givenDoughnutTypeAndDay_whenCalculatingAvaliableDoughnuts_thenReturnExpectedAmount(DoughnutType doughnutType, Day day, int expectedAmount) {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);
        int totalDoughnuts = doughnutProductStockCalculatorService.avaliableDoughnutsForDay(doughnutType, day);

        assertEquals(expectedAmount, totalDoughnuts);
    }

    private static Stream<Arguments> invalidRetrieveDoughnutForDayParameters() {
        return Stream.of(
                Arguments.of(DoughnutType.DOH_NUTS, Day.MONDAY, "Invalid doughnut type: Doh Nuts"),
                Arguments.of(DoughnutType.THE_ONE_TRUE_RING, null, "Day is null")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidRetrieveDoughnutForDayParameters")
    void givenInvalidDoughnutTypeorDay_whenCalculatingAvaliableDoughnuts_thenReturnIllegalArgumentException(DoughnutType doughnutType, Day day, String expectedMessage) {
        doughnuts.remove(1);
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> doughnutProductStockCalculatorService.avaliableDoughnutsForDay(doughnutType, day)
        );

        assertEquals(expectedMessage, exception.getMessage());
    }

    private static Stream<Arguments> removeDoughnutParameters() {
        return Stream.of(
                Arguments.of(Day.SATURDAY, DoughnutType.RING_OF_FIRE, 1, 9),
                Arguments.of(Day.SUNDAY, DoughnutType.RING_OF_FIRE, 1, 4),
                Arguments.of(Day.SATURDAY, DoughnutType.THE_ONE_TRUE_RING, 1, 19),
                Arguments.of(Day.SUNDAY, DoughnutType.THE_ONE_TRUE_RING, 1, 9),
                Arguments.of(Day.SATURDAY, DoughnutType.DOH_NUTS, 1, 29),
                Arguments.of(Day.SUNDAY, DoughnutType.DOH_NUTS, 1, 19)
        );
    }

    @ParameterizedTest
    @MethodSource("removeDoughnutParameters")
    void givenDoughnutTypeAmountAndDay_whenRemovingDoughnuts_thenDoughnutsRemoved(Day day, DoughnutType doughnutType, int removing, int expectedAmount) {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);
        doughnutProductStockCalculatorService.removeDoughnutStock(doughnutType, day, removing);

        Doughnut doughnut = doughnuts.stream()
                .filter(d -> d.getType() == doughnutType)
                .findFirst()
                .orElseThrow();

        assertEquals(expectedAmount, doughnut.getSchedule().get(day));

    }

    private static Stream<Arguments> removeDoughnutInvalidParameters() {
        return Stream.of(
                Arguments.of(null, DoughnutType.RING_OF_FIRE, 1, "Day is null"),
                Arguments.of(Day.SUNDAY, null, 1, "doughnutType is null"),
                Arguments.of(Day.SATURDAY, DoughnutType.THE_ONE_TRUE_RING, 0, "amount must be greater than 0"),
                Arguments.of(Day.SUNDAY, DoughnutType.THE_ONE_TRUE_RING, -1, "amount must be greater than 0"),
                Arguments.of(Day.SATURDAY, DoughnutType.DOH_NUTS, 1, "Invalid doughnut type: Doh Nuts"),
                Arguments.of(Day.SUNDAY, DoughnutType.THE_ONE_TRUE_RING, 21, "Doughnut doesn't have sufficient stock on Sunday for doughnut type The One True Ring")
        );
    }

    @ParameterizedTest
    @MethodSource("removeDoughnutInvalidParameters")
    void givenDoughnutTypeAmountAndDay_whenRemovingDoughnuts_thenDoughnutsRemoved(Day day, DoughnutType doughnutType, int removing, String expectedAmount) {
        doughnuts.remove(1);
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> doughnutProductStockCalculatorService.removeDoughnutStock(doughnutType, day, removing)
        );

        assertEquals(expectedAmount, exception.getMessage());
    }

    private static Stream<Arguments> addDoughnutParameters() {
        return Stream.of(
                Arguments.of(Day.SATURDAY, DoughnutType.RING_OF_FIRE, 1,11),
                Arguments.of(Day.SUNDAY, DoughnutType.RING_OF_FIRE, 1, 6),
                Arguments.of(Day.SATURDAY, DoughnutType.THE_ONE_TRUE_RING, 5, 25),
                Arguments.of(Day.SUNDAY, DoughnutType.THE_ONE_TRUE_RING, 7, 17),
                Arguments.of(Day.SATURDAY, DoughnutType.DOH_NUTS, 4, 34),
                Arguments.of(Day.SUNDAY, DoughnutType.DOH_NUTS, 8, 28)
        );
    }

    @ParameterizedTest
    @MethodSource("addDoughnutParameters")
    void givenDoughnutTypeAmountAndDay_whenAddingDoughnuts_thenDoughnutsAddesToSchedule(Day day, DoughnutType doughnutType, int adding, int expectedAmount) {
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);
        doughnutProductStockCalculatorService.addDoughnutStock(doughnutType, day, adding);

        Doughnut doughnut = doughnuts.stream()
                .filter(d -> d.getType() == doughnutType)
                .findFirst()
                .orElseThrow();

        assertEquals(expectedAmount, doughnut.getSchedule().get(day));
    }

    private static Stream<Arguments> addDoughnutInvalidParameters() {
        return Stream.of(
                Arguments.of(null, DoughnutType.RING_OF_FIRE, 1, "Day is null"),
                Arguments.of(Day.SUNDAY, null, 1, "doughnutType is null"),
                Arguments.of(Day.SATURDAY, DoughnutType.THE_ONE_TRUE_RING, 0, "amount must be greater than 0"),
                Arguments.of(Day.SUNDAY, DoughnutType.THE_ONE_TRUE_RING, -1, "amount must be greater than 0"),
                Arguments.of(Day.SATURDAY, DoughnutType.DOH_NUTS, 1, "Invalid doughnut type: Doh Nuts")
        );
    }

    @ParameterizedTest
    @MethodSource("addDoughnutInvalidParameters")
    void givenInvalidDoughnutTypeAmountOrDay_whenRemovingDoughnuts_thenDoughnutsRemoved(Day day, DoughnutType doughnutType, int adding, String expectedAmount) {
        doughnuts.remove(1);
        doughnutProductStockCalculatorService = new DoughnutStockServiceImpl(doughnuts);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> doughnutProductStockCalculatorService.addDoughnutStock(doughnutType, day, adding)
        );

        assertEquals(expectedAmount, exception.getMessage());
    }
}
