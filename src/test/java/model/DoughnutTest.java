package model;

import org.example.model.Day;
import org.example.model.Doughnut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

class DoughnutTest {
    @Test
    void givenCreateDoughnut_whenGetSetValues_thenCorrectValuesAreReturned() {
        EnumMap<Day, Integer> doughnutSchedule = new EnumMap<>(Day.class);
        doughnutSchedule.put(Day.SATURDAY, 30);
        Doughnut doughnut = new Doughnut("Doh Nuts",3.00, 25, 60, doughnutSchedule);

        Assertions.assertEquals(3.0, doughnut.getPrice());
        Assertions.assertEquals("Doh Nuts", doughnut.getName());
        Assertions.assertEquals(25, doughnut.getFlour());
        Assertions.assertEquals(60, doughnut.getSugar());
        Assertions.assertEquals(doughnutSchedule, doughnut.getSchedule());
    }
}
