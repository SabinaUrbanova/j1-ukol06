package cz.czechitas.ukol06.svatky;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SvatkySluzbaTest {

    @Test
    void vyhledatSvatkyKeDni() throws IOException {
        //implementovat test metody vyhledatSvatkyKeDni
        SvatkySluzba svatkySluzba = new SvatkySluzba();

        List<String> svatkyKeDni = new ArrayList<>();
        svatkyKeDni.add("Alžběta");
        svatkyKeDni.add("Dafné");
        assertEquals(svatkyKeDni, svatkySluzba.vyhledatSvatkyKeDni(MonthDay.of(11, 19)));
        assertTrue(svatkySluzba.vyhledatSvatkyKeDni(MonthDay.of(1, 1)).isEmpty());
    }
}
