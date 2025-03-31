package model;

import org.example.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

class DohNutsTest {

    @Test
    void givenCreateDohNuts_whenGetSetValues_thenCorrectValuesAreReturned() {
        EnumMap<Day, Integer> dohNutsSchedule = new EnumMap<>(Day.class);
        dohNutsSchedule.put(Day.SATURDAY, 30);
        Doughnut doughnut = new DohNuts(3.00, dohNutsSchedule);

        Assertions.assertEquals(3.00, doughnut.getPrice());
        Assertions.assertEquals(DoughnutType.DOH_NUTS, doughnut.getType());
        Assertions.assertEquals(30, doughnut.getFlour());
        Assertions.assertEquals(20, doughnut.getSugar());
        Assertions.assertEquals(dohNutsSchedule, doughnut.getSchedule());
    }
}