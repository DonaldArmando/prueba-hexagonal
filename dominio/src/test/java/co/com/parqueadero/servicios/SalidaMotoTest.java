package co.com.parqueadero.servicios;

import org.junit.Test;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalUnit;

public class SalidaMotoTest {


    @Test
    public void prueba() {

        LocalDateTime inicio = LocalDateTime.of(1992, 10, 20, 10, 0);
        LocalDateTime ultima = LocalDateTime.of(1992, 10, 21, 11, 50);

        Period tiempoTranscurrido = Period.between(inicio.toLocalDate(), ultima.toLocalDate());
        Duration duration = Duration.between(inicio, ultima);

        System.out.println("Dias: " + tiempoTranscurrido.getDays());
        System.out.println("------------------------------");

        System.out.println("Horas: " + Math.ceil(new Double(duration.getSeconds()) / new Double(3600)) + " -- " + duration.getSeconds());
        System.out.println("------------------------------");

        Double valor = (Math.ceil(new Double(duration.getSeconds()) / new Double(3600))) / new Double(24);

        System.out.println("valor decimal: " + valor.intValue());


    }

}
