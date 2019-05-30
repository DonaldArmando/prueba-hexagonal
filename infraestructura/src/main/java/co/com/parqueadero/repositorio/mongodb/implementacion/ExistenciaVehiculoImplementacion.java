package co.com.parqueadero.repositorio.mongodb.implementacion;

import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ExistenciaVehiculoImplementacion implements ExistenciaVehiculo {

    @Override
    public Mono<Boolean> existenciaVehiculo(String placa) {
        return Mono.just(Boolean.FALSE);
    }

}
