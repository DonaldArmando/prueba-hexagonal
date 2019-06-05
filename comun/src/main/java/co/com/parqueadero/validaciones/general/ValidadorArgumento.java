package co.com.parqueadero.validaciones.general;

import co.com.parqueadero.validaciones.exepciones.ExcepcionStringNoVacio;
import co.com.parqueadero.validaciones.exepciones.ExcepcionValidarNoNegativo;
import co.com.parqueadero.validaciones.exepciones.ExcepcionValorObligatorio;

import java.util.Objects;

public final class ValidadorArgumento {

    public static void validarObligatorio(Object valor, String mensaje) {
        if (Objects.isNull(valor)) {
            throw new ExcepcionValorObligatorio(mensaje);
        }
    }

    public static void validarNoNegativo(Integer valor, String mensaje) {
        if (valor < 0) {
            throw new ExcepcionValidarNoNegativo(mensaje);
        }
    }

    public static void validadorStringNoVacio(String valor, String mensaje) {
        if (valor.equals("")) {
            throw new ExcepcionStringNoVacio(mensaje);
        }
    }


}
