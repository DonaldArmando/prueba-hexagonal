package co.com.parqueadero.core.repositorio;

import co.com.parqueadero.core.modelos.Carro;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface SalidaCarro {

    Mono<Tuple2<Carro, Integer>> salidaCarro(String placa);

}
