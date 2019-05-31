package co.com.parqueadero.repositorio.mongodb.implementacion;


import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.SalidaCarro;
import co.com.parqueadero.repositorio.mongodb.convertidores.CarroConvertidor;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public class SalidaCarroImplementacion implements SalidaCarro {

    private final ReactiveMongoOperations reactiveMongoOperations;

    public SalidaCarroImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Carro> salidaCarro(String placa, LocalDateTime fechaSalida) {
        Query query = Query.query(
                Criteria
                        .where(Constantes.REGISTRO_PLACA)
                        .is(placa)
                        .and("fechaSalida")
                        .is(null)
        );
        Update update = Update.update("fechaSalida", fechaSalida);
        return this.reactiveMongoOperations.findAndModify(query, update, Registro.class)
                .map(CarroConvertidor::registroCarro);
    }


}
