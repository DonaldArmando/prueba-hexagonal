package co.com.parqueadero.validaciones.general;

import co.com.parqueadero.validaciones.exepciones.ExcepcionFechaNoAnterior;

import java.time.LocalDateTime;

public final class ValidadorFecha {

    private ValidadorFecha() {
    }

    public static void validarFechaNoAnterior(LocalDateTime fecha, String mmensaje) {
        if (LocalDateTime.now().isBefore(fecha)) {
            throw new ExcepcionFechaNoAnterior(mmensaje);
        }
    }


}
