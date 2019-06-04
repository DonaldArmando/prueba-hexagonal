package co.com.parqueadero.repositorio.mongodb.implementacion;

import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ExistenciaVehiculoImplementacion implements ExistenciaVehiculo {

    private final ReactiveMongoOperations reactiveMongoOperations;


    public ExistenciaVehiculoImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Boolean> existenciaVehiculo(String placa) {
        Query existenciaVehiculo = Query.query(
                Criteria
                        .where(Constantes.REGISTRO_PLACA)
                        .is(placa)
                        .and(Constantes.FECHA_SALIDA)
                        .is(null)

        );

        return this.reactiveMongoOperations.exists(existenciaVehiculo, Registro.class);
    }

}
