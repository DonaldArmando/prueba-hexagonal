package co.com.parqueadero.servicios;

import co.com.parqueadero.TestDataBuilder.MotoTestDataBuilder;
import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarMoto;
import co.com.parqueadero.core.servicios.RegistrarMoto;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RegistrarMotoTest {


    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistrada() {

        // Arrange
        Moto moto = new MotoTestDataBuilder().build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo .class);
        RegistrarMoto registrarMoto = new RegistrarMoto(ingresarMoto,existenciaVehiculo);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));


        // Act
        Mono<Moto> result = registrarMoto.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectNext(moto).verifyComplete();




    }


}
