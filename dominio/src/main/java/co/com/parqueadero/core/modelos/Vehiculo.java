package co.com.parqueadero.core.modelos;

import co.com.parqueadero.validaciones.general.ValidadorArgumento.*;

import java.time.LocalDateTime;

import static co.com.parqueadero.validaciones.general.ValidadorArgumento.validarObligatorio;
import static co.com.parqueadero.validaciones.general.ValidadorFecha.validarFechaNoAnterior;
import static co.com.parqueadero.validaciones.general.ValidadorArgumento.validarNoNegativo;
import static co.com.parqueadero.validaciones.general.ValidadorArgumento.validadorStringNoVacio;

public class Vehiculo {

    private static final String SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO = "Se debe ingresar la placa del vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA = "Se debe ingresar la fecha de entrada";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_HORA = "Se debe ingresar el valor hora";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_DIA = "Se debe ingresar el valor dia";
    private static final String SE_DEBE_REGISTRAR_UNA_FECHA_MAYOR_O_IGUAL_A_LA_ACTUAL = "Se debe registrar una fecha mayor o igual a la actual";
    private static final String SE_DEBE_INGRESAR_UN_VALOR_NO_NEGATIVO_PARA_EL_VALOR_DE_LA_HORA = "Se debe ingresar un valor no negativo para el valor de la hora";
    private static final String SE_DEBE_INGRESAR_UN_VALOR_NO_NEGATIVO_PARA_EL_VALOR_DEL_DIA = "Se debe ingresar un valor no negativo para el valor deL dia";
    private static final String SE_DEBE_INGRESAR_UNA_PLACA_NO_VACIA = "Se dbe ingresar una placa no vacia";

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;

    public Vehiculo(String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Integer valorHora, Integer valorDia) {
        validarObligatorio(placa, SE_DEBE_INGRESAR_LA_PLACA_DEL_VEHICULO);
        validarObligatorio(fechaEntrada, SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA);
        validarObligatorio(valorHora, SE_DEBE_INGRESAR_EL_VALOR_HORA);
        validarObligatorio(valorDia, SE_DEBE_INGRESAR_EL_VALOR_DIA);
        validarFechaNoAnterior(fechaEntrada, SE_DEBE_REGISTRAR_UNA_FECHA_MAYOR_O_IGUAL_A_LA_ACTUAL);
        validarNoNegativo(valorHora, SE_DEBE_INGRESAR_UN_VALOR_NO_NEGATIVO_PARA_EL_VALOR_DE_LA_HORA);
        validarNoNegativo(valorDia, SE_DEBE_INGRESAR_UN_VALOR_NO_NEGATIVO_PARA_EL_VALOR_DEL_DIA);
        validadorStringNoVacio(placa, SE_DEBE_INGRESAR_UNA_PLACA_NO_VACIA);

        this.placa = placa;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorHora = valorHora;
        this.valorDia = valorDia;
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public Integer getValorHora() {
        return valorHora;
    }

    public Integer getValorDia() {
        return valorDia;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", valorHora=" + valorHora +
                ", valorDia=" + valorDia +
                '}';
    }
}
