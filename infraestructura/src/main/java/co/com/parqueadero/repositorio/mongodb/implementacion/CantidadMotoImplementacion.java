package co.com.parqueadero.repositorio.mongodb.implementacion;

import co.com.parqueadero.core.repositorio.CantidadMoto;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class CantidadMotoImplementacion implements CantidadMoto {


    private final ReactiveMongoOperations reactiveMongoOperations;


    public CantidadMotoImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Long> cantidadMoto() {

        Query encontrarMotosGeneral = Query.query(
                Criteria
                        .where(Constantes.FECHA_SALIDA)
                        .is(null)
                        .and(Constantes.TIPO)
                        .is(VehiculoType.MOTO)
        );

        return this.reactiveMongoOperations.count(encontrarMotosGeneral, Registro.class);
    }


}
