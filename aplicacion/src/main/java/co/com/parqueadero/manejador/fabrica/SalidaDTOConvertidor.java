package co.com.parqueadero.manejador.fabrica;

import co.com.parqueadero.manejador.fabrica.dto.SalidaDTO;

import java.math.BigInteger;

public class SalidaDTOConvertidor {


    public SalidaDTO salidaDTO(Object object, BigInteger valorSalida) {
        return new SalidaDTO(object, valorSalida);
    }


}
