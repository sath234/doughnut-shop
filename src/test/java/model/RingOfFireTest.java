package model;

import org.example.model.Day;
import org.example.model.RingOfFire;
import org.example.model.Doughnut;
import org.example.model.DoughnutType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

class RingOfFireTest {
    @Test
    void givenCreateRingOfFire_whenGetSetValues_thenCorrectValuesAreReturned() {
        EnumMap<Day, Integer> ringOfFireSchedule = new EnumMap<>(Day.class);
        ringOfFireSchedule.put(Day.SATURDAY, 30);
        Doughnut doughnut = new RingOfFire(3.00, ringOfFireSchedule);

        Assertions.assertEquals(3.00, doughnut.getPrice());
        Assertions.assertEquals(DoughnutType.RING_OF_FIRE, doughnut.getType());
        Assertions.assertEquals(30, doughnut.getFlour());
        Assertions.assertEquals(15, doughnut.getSugar());
        Assertions.assertEquals(ringOfFireSchedule, doughnut.getSchedule());
    }
}
