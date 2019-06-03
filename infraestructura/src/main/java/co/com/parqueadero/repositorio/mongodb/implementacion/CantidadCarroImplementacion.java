package co.com.parqueadero.repositorio.mongodb.implementacion;

import co.com.parqueadero.core.repositorio.CantidadCarro;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public class CantidadCarroImplementacion implements CantidadCarro {

    private final ReactiveMongoOperations reactiveMongoOperations;

    public CantidadCarroImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Long> cantidadCarro() {

        Query encontrarMotosGeneral = Query.query(
                Criteria
                        .where(Constantes.FECHA_SALIDA)
                        .is(null)
                        .and(Constantes.TIPO)
                        .is(VehiculoType.CARRO)
        );

        return this.reactiveMongoOperations.count(encontrarMotosGeneral, Registro.class);
    }

}
