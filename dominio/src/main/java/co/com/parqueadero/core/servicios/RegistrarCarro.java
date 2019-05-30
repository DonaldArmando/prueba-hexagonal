package co.com.parqueadero.core.servicios;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarCarro;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import reactor.core.publisher.Mono;


public class RegistrarCarro {

    private static final String EL_CARRO_AUN_NO_SE_LIQUIDA = "El carro aun no se liquida";

    private final IngresarCarro ingresarCarro;
    private final ExistenciaVehiculo existenciaVehiculo;


    public RegistrarCarro(IngresarCarro ingresarCarro, ExistenciaVehiculo existenciaVehiculo) {
        this.ingresarCarro = ingresarCarro;
        this.existenciaVehiculo = existenciaVehiculo;
    }

    public Mono<Carro> ejecutar(Carro carro) {
        return this.validarExistenciaPrevia(carro.getPlaca())
                .map(valor -> carro)
                .flatMap(ingresarCarro::ingresarCarro);
    }

    private Mono<Boolean> validarExistenciaPrevia(String placa) {
        return this.existenciaVehiculo.existenciaVehiculo(placa)
                .map(existe -> {
                    if (existe) {
                        throw new ExcepcionDuplicidad(EL_CARRO_AUN_NO_SE_LIQUIDA);
                    }
                    return existe;
                });
    }

}
