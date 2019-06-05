package co.com.parqueadero.repositorio.mongodb.implementacion;


import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.SalidaMoto;
import co.com.parqueadero.repositorio.mongodb.convertidores.MotoConvertidor;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public class SalidaMotoImplementacion implements SalidaMoto {

    private final ReactiveMongoOperations reactiveMongoOperations;

    public SalidaMotoImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Moto> salidaMoto(String placa, LocalDateTime fechaSalida) {
        Query consultaEncontrarSinfechaSalida = Query.query(
                Criteria
                        .where(Constantes.REGISTRO_PLACA)
                        .is(placa)
                        .and(Constantes.FECHA_SALIDA)
                        .is(null)
                        .and(Constantes.TIPO)
                        .is(VehiculoType.MOTO)
        );

        Query encontrarPorPlaca = Query.query(
                Criteria
                        .where(Constantes.REGISTRO_PLACA)
                        .is(placa)
                        .and(Constantes.TIPO)
                        .is(VehiculoType.MOTO)
        );

        Update update = Update.update(Constantes.FECHA_SALIDA, fechaSalida);
        return this.reactiveMongoOperations.upsert(consultaEncontrarSinfechaSalida, update, Registro.class)
                .flatMap(resultSet -> this.reactiveMongoOperations.findOne(encontrarPorPlaca, Registro.class))
                .map(MotoConvertidor::registroMoto);
    }

}
