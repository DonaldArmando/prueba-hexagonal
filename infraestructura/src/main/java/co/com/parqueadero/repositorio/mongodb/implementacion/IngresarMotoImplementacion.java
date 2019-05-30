package co.com.parqueadero.repositorio.mongodb.implementacion;


import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarMoto;
import co.com.parqueadero.repositorio.mongodb.convertidores.MotoConvertidor;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class IngresarMotoImplementacion implements IngresarMoto{


    private final ReactiveMongoOperations reactiveMongoOperations;


    public IngresarMotoImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Moto> ingresarMoto(Moto moto) {
        return Mono.just(moto)
                .map(MotoConvertidor::motoRegistro)
                .flatMap(reactiveMongoOperations::save)
                .map(MotoConvertidor::registroMoto);
    }



}
