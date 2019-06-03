package co.com.parqueadero.repositorio.mongodb.implementacion;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.ConsultarCarro;
import co.com.parqueadero.repositorio.mongodb.convertidores.CarroConvertidor;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ConsultarCarroImplementacion implements ConsultarCarro {

    private final ReactiveMongoOperations reactiveMongoOperations;


    public ConsultarCarroImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }


    @Override
    public Mono<Carro> consultarCarro(String placa) {
        Criteria criteria = Criteria
                .where(Constantes.REGISTRO_PLACA)
                .is(placa)
                .and(Constantes.TIPO)
                .is(VehiculoType.CARRO)
                .and(Constantes.FECHA_SALIDA)
                .is(null);
        Query query = Query.query(criteria);
        return this.reactiveMongoOperations
                .findOne(query, Registro.class)
                .map(CarroConvertidor::registroCarro);
    }

}
