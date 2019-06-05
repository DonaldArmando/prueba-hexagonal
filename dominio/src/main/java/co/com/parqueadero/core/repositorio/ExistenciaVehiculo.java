package co.com.parqueadero.core.repositorio;

import reactor.core.publisher.Mono;

public interface ExistenciaVehiculo {

    Mono<Boolean> existenciaVehiculo(String placa);

}
