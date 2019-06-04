package co.com.parqueadero.core.repositorio;

import co.com.parqueadero.core.modelos.Moto;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDateTime;

public interface SalidaMoto {

    Mono<Moto> salidaMoto(String placa, LocalDateTime fechaSalida);

}
