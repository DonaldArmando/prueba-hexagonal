package co.com.parqueadero.core.repositorio;

import reactor.core.publisher.Mono;

public interface CantidadCarro {

    Mono<Long> cantidadCarro();

}
