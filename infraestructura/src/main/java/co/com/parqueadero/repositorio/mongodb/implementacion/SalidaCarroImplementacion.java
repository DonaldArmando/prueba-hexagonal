package co.com.parqueadero.repositorio.mongodb.implementacion;


import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.SalidaCarro;
import co.com.parqueadero.repositorio.mongodb.convertidores.CarroConvertidor;
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
public class SalidaCarroImplementacion implements SalidaCarro {

    private final ReactiveMongoOperations reactiveMongoOperations;

    public SalidaCarroImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Carro> salidaCarro(String placa, LocalDateTime fechaSalida) {
        Query consultaEncontrarSinfechaSalida = Query.query(
                Criteria
                        .where(Constantes.REGISTRO_PLACA)
                        .is(placa)
                        .and(Constantes.FECHA_SALIDA)
                        .is(null)
                        .and(Constantes.TIPO)
                        .is(VehiculoType.CARRO)
        );

        Query encontrarPorPlaca = Query.query(
                Criteria
                        .where(Constantes.REGISTRO_PLACA)
                        .is(placa)
                        .and(Constantes.TIPO)
                        .is(VehiculoType.CARRO)
        );

        Update update = Update.update(Constantes.FECHA_SALIDA, fechaSalida);
        return this.reactiveMongoOperations.upsert(consultaEncontrarSinfechaSalida, update, Registro.class)
                .flatMap(ignore -> this.reactiveMongoOperations.findOne(encontrarPorPlaca, Registro.class))
                .map(CarroConvertidor::registroCarro);
    }


}
