package co.com.parqueadero.servicios;

import co.com.parqueadero.TestDataBuilder.CarroTestDataBuilder;
import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarCarro;
import co.com.parqueadero.core.servicios.RegistrarCarro;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RegistrarCarroTest {

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistrada() {
        // Arrange
        Carro carro = new CarroTestDataBuilder().build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        RegistrarCarro registrarCarro = new RegistrarCarro(ingresarCarro, existenciaVehiculo);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoSiExistePlacaRegistradaValidarExcepcion() {
        // Arrange
        Carro carro = new CarroTestDataBuilder().build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        RegistrarCarro registrarCarro = new RegistrarCarro(ingresarCarro, existenciaVehiculo);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectError(ExcepcionDuplicidad.class).verify();
    }

    @Test
    public void ejecutarExitosoCuandoSiExistePlacaRegistradaValidarExcepcionMensaje() {
        // Arrange
        Carro carro = new CarroTestDataBuilder().build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        RegistrarCarro registrarCarro = new RegistrarCarro(ingresarCarro, existenciaVehiculo);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectErrorMessage(RegistrarCarro.getElCarroAunNoSeLiquida()).verify();
    }


}
