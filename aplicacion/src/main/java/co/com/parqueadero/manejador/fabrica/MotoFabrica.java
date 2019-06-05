package co.com.parqueadero.manejador.fabrica;


import co.com.parqueadero.core.configuracion.ConfiguracionGeneral;
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
                ConfiguracionGeneral.VALOR_HORA_MOTO,
                ConfiguracionGeneral.VALOR_DIA_MOTO,
                motoDTO.getCilindraje()
        );
    }


}
