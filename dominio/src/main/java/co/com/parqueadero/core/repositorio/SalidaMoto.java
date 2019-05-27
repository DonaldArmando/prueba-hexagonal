package co.com.parqueadero.core.repositorio;

import co.com.parqueadero.core.modelos.Moto;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public interface SalidaMoto {

    Mono<Tuple2<Moto, Integer>> salidaMoto(String placa);

}
