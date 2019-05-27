package co.com.parqueadero.validaciones.exepciones;

public class ExcepcionFechaNoAnterior extends RuntimeException {

    public ExcepcionFechaNoAnterior(String message) {
        super(message);
    }

}
