package model;

import org.example.model.Day;
import org.example.model.RingOfFire;
import org.example.model.Doughnut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

class RingOfFireTest {
    @Test
    void givenCreateRingOfFire_whenGetSetValues_thenCorrectValuesAreReturned() {
        Doughnut doughnut = new RingOfFire();

        Assertions.assertEquals(2.50, doughnut.getPrice());
        Assertions.assertEquals("Ring Of Fire", doughnut.getName());
        Assertions.assertEquals(30, doughnut.getFlour());
        Assertions.assertEquals(15, doughnut.getSugar());
        Assertions.assertEquals(Map.of(Day.SATURDAY, 10, Day.SUNDAY, 5), doughnut.getSchedule());
    }
}
