package co.com.parqueadero.manejador.fabrica;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;
import co.com.parqueadero.validaciones.utilidades.UtilidadFecha;

import java.time.LocalDateTime;

public class CarroFabrica {

    private final UtilidadFecha utilidadFecha;


    public CarroFabrica(UtilidadFecha utilidadFecha) {
        this.utilidadFecha = utilidadFecha;
    }

    public Carro carroDTOCarro(CarroDTO carroDTO) {
        return new Carro(
                carroDTO.getPlaca(),
                utilidadFecha.fechaActual(),
                null,
                1000,
                9000
        );
    }

}
