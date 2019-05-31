package co.com.parqueadero.repositorio.mongodb.implementacion;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.ConsultarMoto;
import co.com.parqueadero.repositorio.mongodb.convertidores.MotoConvertidor;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static co.com.parqueadero.repositorio.mongodb.enums.Constantes.TIPO;


@Repository
public class ConsultarMotoImplementacion implements ConsultarMoto {

    private final ReactiveMongoOperations reactiveMongoOperations;


    public ConsultarMotoImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }


    @Override
    public Mono<Moto> consultarMoto(String placa) {
        Criteria criteria = Criteria
                .where(Constantes.REGISTRO_PLACA)
                .is(placa)
                .and(Constantes.TIPO)
                .is(VehiculoType.MOTO);
        Query query = Query.query(criteria);
        return this.reactiveMongoOperations
                .findOne(query, Registro.class)
                .map(MotoConvertidor::registroMoto);
    }

}
