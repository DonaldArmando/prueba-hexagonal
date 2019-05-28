package co.com.parqueadero.manejador.moto;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.servicios.RegistrarMoto;
import co.com.parqueadero.manejador.fabrica.MotoFabrica;
import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;
import reactor.core.publisher.Mono;

public class ManejadorIngresarMoto {

    private final RegistrarMoto registrarMoto;
    private final MotoFabrica motoFabrica;


    public ManejadorIngresarMoto(RegistrarMoto registrarMoto, MotoFabrica motoFabrica) {
        this.registrarMoto = registrarMoto;
        this.motoFabrica = motoFabrica;
    }

    public Mono<Moto> ejecutar(MotoDTO motoDTO) {
        Moto moto = this.motoFabrica.motoDTOMoto(motoDTO);
        return this.registrarMoto.ejecutar(moto);
    }

}
