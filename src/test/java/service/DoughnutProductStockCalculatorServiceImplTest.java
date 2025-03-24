package service;

import org.example.model.Day;
import org.example.model.DoughnutProduct;
import org.example.model.DoughnutSchedule;
import org.example.model.DoughnutType;
import org.example.service.DoughnutStockCalculatorService;
import org.example.service.DoughnutStockCalculatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DoughnutProductStockCalculatorServiceImplTest {

    private List<DoughnutSchedule> schedules;

    @BeforeEach
    void setUp() {
        EnumMap<Day, Integer> theOneTrueRingSchedule = new EnumMap<>(Day.class);
        theOneTrueRingSchedule.put(Day.SATURDAY, 20);

        EnumMap<Day, Integer> dohNutsSchedule = new EnumMap<>(Day.class);
        dohNutsSchedule.put(Day.SATURDAY, 30);

        EnumMap<Day, Integer> ringOfFireSchedule = new EnumMap<>(Day.class);
        ringOfFireSchedule.put(Day.SATURDAY, 10);

        schedules = new ArrayList<>(List.of(
                new DoughnutSchedule(new DoughnutProduct(DoughnutType.THE_ONE_TRUE_RING, 3.00), theOneTrueRingSchedule),
                new DoughnutSchedule(new DoughnutProduct(DoughnutType.DOH_NUTS, 2.50), dohNutsSchedule),
                new DoughnutSchedule(new DoughnutProduct(DoughnutType.RING_OF_FIRE, 2.50), ringOfFireSchedule)
        ));
    }

    @Test
    void testTotalFlourForEachDoughnutType() {
        DoughnutStockCalculatorService service = new DoughnutStockCalculatorServiceImpl(schedules);
        int totalFlour = DoughnutType.THE_ONE_TRUE_RING.getFlourRequired() + DoughnutType.RING_OF_FIRE.getFlourRequired() + DoughnutType.DOH_NUTS.getFlourRequired();
        assertEquals(totalFlour, service.getTotalFlourForEachDoughnutType());
    }
}
