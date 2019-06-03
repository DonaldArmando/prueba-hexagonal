package co.com.parqueadero.validaciones.exepciones;

public class ExcepcionMaximoCupos extends RuntimeException {

    public ExcepcionMaximoCupos(String mensaje) {
        super(mensaje);
    }
}
