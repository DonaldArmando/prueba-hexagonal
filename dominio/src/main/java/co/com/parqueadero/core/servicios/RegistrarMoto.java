package co.com.parqueadero.core.servicios;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarMoto;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class RegistrarMoto {

    private static final String LA_MOTO_AUN_NO_SE_LIQUIDA = "La moto aun no se liquida";

    private final IngresarMoto ingresarMoto;
    private final ExistenciaVehiculo existenciaVehiculo;


    public RegistrarMoto(IngresarMoto ingresarMoto, ExistenciaVehiculo existenciaVehiculo) {
        this.ingresarMoto = ingresarMoto;
        this.existenciaVehiculo = existenciaVehiculo;
    }

    public Mono<Moto> ejecutar(Moto moto) {
        return this.validarExistenciaPrevia(moto.getPlaca())
                .map(valor -> moto)
                .flatMap(ingresarMoto::ingresarMoto);
    }

    private Mono<Boolean> validarExistenciaPrevia(String placa) {
        return this.existenciaVehiculo.existenciaVehiculo(placa)
                .map(existe -> {
                    if (existe) {
                        throw new ExcepcionDuplicidad(LA_MOTO_AUN_NO_SE_LIQUIDA);
                    }
                    return existe;
                });
    }

}
