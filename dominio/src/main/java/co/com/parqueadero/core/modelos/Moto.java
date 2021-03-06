package co.com.parqueadero.core.modelos;

import java.time.LocalDateTime;
import java.util.Objects;

import static co.com.parqueadero.validaciones.general.ValidadorArgumento.validarObligatorio;
import static co.com.parqueadero.validaciones.general.ValidadorArgumento.validarNoNegativo;

public class Moto extends Vehiculo {

    private static final String SE_DEBE_INGRESAR_UN_VALOR_NO_NEGATIVO_PARA_EL_VALOR_DEL_CILINDRAJE = "Se debe ingresar un valor no negativo para el valor deL cilindraje";
    private static final String SE_DEBE_INGRESAR_EL_CILINDRAJE_DE_LA_MOTO = "Se debe ingresar el cilindraje de la moto";

    private Integer cilindraje;

    public Moto(String placa,
                LocalDateTime fechaEntrada,
                LocalDateTime fechaSalida,
                Integer valorHora,
                Integer valorDia,
                Integer cilindraje) {
        super(placa, fechaEntrada, fechaSalida, valorHora, valorDia);

        validarNoNegativo(cilindraje, SE_DEBE_INGRESAR_UN_VALOR_NO_NEGATIVO_PARA_EL_VALOR_DEL_CILINDRAJE);
        validarObligatorio(cilindraje, SE_DEBE_INGRESAR_EL_CILINDRAJE_DE_LA_MOTO);
        this.cilindraje = cilindraje;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "cilindraje=" + cilindraje +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moto moto = (Moto) o;
        return Objects.equals(cilindraje, moto.cilindraje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cilindraje);
    }

    public Integer getCilindraje() {
        return cilindraje;
    }
}



