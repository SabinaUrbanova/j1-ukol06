package cz.czechitas.ukol06.svatky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.time.MonthDay;
import java.util.List;
import java.util.stream.Stream;

public class SvatkySluzba {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    private final Path cestaKDatum = Path.of("data/svatky.json");

    private  SeznamSvatku seznamSvatku;

    public SvatkySluzba() {
        //  načíst seznam svátků ze souboru svatky.json
        try {
            this.seznamSvatku = objectMapper.readValue(cestaKDatum.toFile(), SeznamSvatku.class);
        } catch (IOException e) {
            System.err.println("Nepodarilo se načíst  seznam svátků.");
        }
    }


    public List<String> vyhledatSvatkyDnes() {
        return vyhledatSvatkyKeDni(MonthDay.now());
    }

    public List<String> vyhledatSvatkyKeDni(MonthDay day) {
        // získat seznam svátků
        List<Svatek> svatkyKeDni = seznamSvatku.getSvatky();
        // převést na Stream
        Stream<Svatek> streamSvatku = svatkyKeDni.stream();
        // pomocí metody filter() vybrat jen ty, které odpovídají zadanému dni (porovnat MonthDay pomocí metodyequals())
        // pomocí metody map() získat z objektu jméno
        streamSvatku = streamSvatku.filter(svatek -> svatek.getDen().equals(day));
        Stream<String>  streamJmen = streamSvatku.map(Svatek::getJmeno);
        // pomocí toList() převést na List
        return streamJmen.toList();
    }
}

