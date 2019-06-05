package co.com.parqueadero.core.repositorio;

import co.com.parqueadero.core.modelos.Carro;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface SalidaCarro {

    Mono<Carro> salidaCarro(String placa, LocalDateTime fechaSalida);

}
