package co.com.parqueadero.manejador.fabrica.dto;

import java.math.BigInteger;


public class SalidaDTO {

    private Object registro;
    private BigInteger valorSalida;

    public SalidaDTO(Object registro, BigInteger valorSalida) {
        this.registro = registro;
        this.valorSalida = valorSalida;
    }

    public SalidaDTO() {
    }

    public Object getRegistro() {
        return registro;
    }

    public void setRegistro(Object registro) {
        this.registro = registro;
    }

    public BigInteger getValorSalida() {
        return valorSalida;
    }

    public void setValorSalida(BigInteger valorSalida) {
        this.valorSalida = valorSalida;
    }
}
