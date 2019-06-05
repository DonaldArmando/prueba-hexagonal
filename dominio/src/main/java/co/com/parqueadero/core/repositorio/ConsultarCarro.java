package co.com.parqueadero.core.repositorio;

import co.com.parqueadero.core.modelos.Carro;
import reactor.core.publisher.Mono;

public interface ConsultarCarro {

    Mono<Carro> consultarCarro(String placa);

}
