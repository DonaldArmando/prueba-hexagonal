package co.com.parqueadero.manejador.fabrica;


import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;

import java.time.LocalDateTime;

public class MotoFabrica {


    public Moto motoDTOMoto(MotoDTO motoDTO) {
        return new Moto(
                motoDTO.getPlaca(),
                LocalDateTime.now(),
                null,
                1000,
                9000,
                motoDTO.getCilindraje()
        );
    }


}
