package model;

import org.example.model.Day;
import org.example.model.TheOneTrueRing;
import org.example.model.Doughnut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

class TheOneTrueRingTest {
    @Test
    void givenCreateTheOneTrueRing_whenGetSetValues_thenCorrectValuesAreReturned() {
        Doughnut doughnut = new TheOneTrueRing();

        Assertions.assertEquals(3.00, doughnut.getPrice());
        Assertions.assertEquals("The One True Ring", doughnut.getName());
        Assertions.assertEquals(60, doughnut.getFlour());
        Assertions.assertEquals(30, doughnut.getSugar());
        Assertions.assertEquals(Map.of(Day.SATURDAY, 20, Day.SUNDAY, 10), doughnut.getSchedule());
    }
}
