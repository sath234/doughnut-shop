package service;

import org.example.model.DohNuts;
import org.example.model.Doughnut;
import org.example.model.RingofFire;
import org.example.model.TheOneTrueRing;
import org.example.service.DoughnutProductStockCalculatorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class DoughnutProductStockCalculatorServiceImplTest {
    private DoughnutProductStockCalculatorServiceImpl doughnutProductStockCalculatorService;
    private List<Doughnut> doughnutList;
    private HashMap<String, List<Doughnut>> weekSchedule;

    @BeforeEach
    void setUp() {
        doughnutList = new ArrayList<>(
                List.of(
                        new TheOneTrueRing(3.00, 0),
                        new DohNuts(2.50, 0),
                        new RingofFire(2.50, 0)
                )
        );

        weekSchedule = new HashMap<>();

        weekSchedule.put("Saturday", new ArrayList<>(List.of(
                new TheOneTrueRing(3.00, 20),
                new DohNuts(2.50, 30),
                new RingofFire(2.50, 10)
        )));

        weekSchedule.put("Sunday", new ArrayList<>(List.of(
                new TheOneTrueRing(3.00, 10),
                new DohNuts(2.50, 20),
                new RingofFire(2.50, 5)
        )));
    }

    @Test
    void testTotalFlourForEachDoughnutType() {
        doughnutProductStockCalculatorService = new DoughnutProductStockCalculatorServiceImpl(weekSchedule, doughnutList);
        int totalFlour = doughnutProductStockCalculatorService.calculateFlourForEachDoughnut();
        Assertions.assertEquals(120, totalFlour);
    }

    private static Stream<Arguments> weekScheduleProvider() {
        return Stream.of(
                Arguments.of("Saturday", 1350),
                Arguments.of("Sunday", 775)
        );
    }

    @ParameterizedTest
    @MethodSource("weekScheduleProvider")
    void testTotalFlourForEachDoughnutType(String day, int expectedFlour) {
        doughnutProductStockCalculatorService = new DoughnutProductStockCalculatorServiceImpl(weekSchedule, doughnutList);
        int totalFlour = doughnutProductStockCalculatorService.calculateTotalSugarForDay(day);

        Assertions.assertEquals(expectedFlour, totalFlour);
    }


}
