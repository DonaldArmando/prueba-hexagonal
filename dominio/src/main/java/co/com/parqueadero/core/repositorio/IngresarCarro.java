package co.com.parqueadero.core.repositorio;

import co.com.parqueadero.core.modelos.Carro;
import reactor.core.publisher.Mono;

public interface IngresarCarro {

    Mono<Carro> ingresarCarro(Carro carro);

}
