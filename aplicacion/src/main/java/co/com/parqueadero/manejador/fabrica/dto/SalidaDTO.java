package co.com.parqueadero.manejador.fabrica.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class SalidaDTO {

    private Object registro;
    private BigInteger valorSalida;

    public SalidaDTO(Object registro, BigInteger valorSalida) {
        this.registro = registro;
        this.valorSalida = valorSalida;
    }
}
