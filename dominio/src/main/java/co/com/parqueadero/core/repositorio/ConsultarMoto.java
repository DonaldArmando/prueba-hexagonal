package co.com.parqueadero.core.repositorio;

import co.com.parqueadero.core.modelos.Moto;
import reactor.core.publisher.Mono;

public interface ConsultarMoto {

    Mono<Moto> consultarMoto(String placa);

}
