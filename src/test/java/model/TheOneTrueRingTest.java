package model;

import org.example.model.Day;
import org.example.model.TheOneTrueRing;
import org.example.model.Doughnut;
import org.example.model.DoughnutType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

class TheOneTrueRingTest {
    @Test
    void givenCreateTheOneTrueRing_whenGetSetValues_thenCorrectValuesAreReturned() {
        EnumMap<Day, Integer> theOneTrueRingSchedule = new EnumMap<>(Day.class);
        theOneTrueRingSchedule.put(Day.SATURDAY, 30);
        Doughnut doughnut = new TheOneTrueRing(3.00, theOneTrueRingSchedule);

        Assertions.assertEquals(3.00, doughnut.getPrice());
        Assertions.assertEquals(DoughnutType.THE_ONE_TRUE_RING, doughnut.getType());
        Assertions.assertEquals(60, doughnut.getFlour());
        Assertions.assertEquals(30, doughnut.getSugar());
        Assertions.assertEquals(theOneTrueRingSchedule, doughnut.getSchedule());
    }
}
