package co.com.parqueadero.manejador.moto;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.servicios.RegistrarMoto;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class ManejadorIngresarMoto {

   /* private final RegistrarMoto registrarMoto;


    public ManejadorIngresarMoto(RegistrarMoto registrarMoto) {
        this.registrarMoto = registrarMoto;
    }*/

    public Mono<Moto> ejecutar(Moto moto) {
        return Mono.just(new Moto("a", LocalDateTime.now(),LocalDateTime.now(),1,1,1));

        //return this.registrarMoto.ejecutar(moto);
    }

}
