package co.com.parqueadero.repositorio.mongodb.implementacion;


import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.IngresarCarro;
import co.com.parqueadero.repositorio.mongodb.convertidores.CarroConvertidor;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class IngresarCarroImplementacion implements IngresarCarro {

    private final ReactiveMongoOperations reactiveMongoOperations;


    public IngresarCarroImplementacion(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Carro> ingresarCarro(Carro carro) {
        return Mono.just(carro)
                .map(CarroConvertidor::carroRegistro)
                .flatMap(reactiveMongoOperations::save)
                .map(CarroConvertidor::registroCarro);
    }



}
