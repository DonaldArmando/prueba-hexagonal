package co.com.parqueadero.manejador.fabrica;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;

import java.time.LocalDateTime;

public class CarroFabrica {

    public Carro carroDTOCarro(CarroDTO carroDTO) {
        return new Carro(
                carroDTO.getPlaca(),
                LocalDateTime.now(),
                null,
                1000,
                9000
        );
    }

}
