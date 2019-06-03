package co.com.parqueadero.manejador.fabrica;


import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;
import co.com.parqueadero.validaciones.utilidades.UtilidadFecha;

import java.time.LocalDateTime;

public class MotoFabrica {

    private final UtilidadFecha utilidadFecha;

    public MotoFabrica(UtilidadFecha utilidadFecha) {
        this.utilidadFecha = utilidadFecha;
    }

    public Moto motoDTOMoto(MotoDTO motoDTO) {
        return new Moto(
                motoDTO.getPlaca(),
                utilidadFecha.fechaActual(),
                null,
                1000,
                9000,
                motoDTO.getCilindraje()
        );
    }


}
