package model;

import org.example.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

class DohNutsTest {

    @Test
    void givenCreateDohNuts_whenGetSetValues_thenCorrectValuesAreReturned() {
        Doughnut doughnut = new DohNuts();

        Assertions.assertEquals(2.50, doughnut.getPrice());
        Assertions.assertEquals("Doh Nuts", doughnut.getName());
        Assertions.assertEquals(30, doughnut.getFlour());
        Assertions.assertEquals(20, doughnut.getSugar());
        Assertions.assertEquals(Map.of(Day.SATURDAY, 30, Day.SUNDAY, 20), doughnut.getSchedule());
    }
}