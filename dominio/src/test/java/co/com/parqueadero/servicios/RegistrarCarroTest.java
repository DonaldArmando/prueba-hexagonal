package co.com.parqueadero.servicios;

import co.com.parqueadero.TestDataBuilder.CarroTestDataBuilder;
import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.CantidadCarro;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarCarro;
import co.com.parqueadero.core.servicios.RegistrarCarro;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDiaSemana;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import co.com.parqueadero.validaciones.exepciones.ExcepcionMaximoCupos;
import co.com.parqueadero.validaciones.utilidades.UtilidadFecha;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrarCarroTest {

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistrada() {
        // Arrange
        Carro carro = new CarroTestDataBuilder().build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYEsDomingo() {
        // Arrange
        Carro carro = new CarroTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 02, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYEsDomingoYminusculaLaPrimeraLetra() {
        // Arrange
        Carro carro = new CarroTestDataBuilder()
                .conPlaca("abc-123")
                .build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 02, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYNoEsNiLunesNiDomingo() {
        // Arrange
        Carro carro = new CarroTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 04, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectError(ExcepcionDiaSemana.class).verify();
    }


    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYEsLunes() {
        // Arrange
        Carro carro = new CarroTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();
    }


    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYNoEsLunesExcepcion() {
        // Arrange
        Carro carro = new CarroTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 04, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectError(ExcepcionDiaSemana.class).verify();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaDiferenteDeAYNoEsNiDomingoNiLunes() {
        // Arrange
        Carro carro = new CarroTestDataBuilder()
                .conPlaca("CBA-123")
                .build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 04, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYNoEsLunesExcepcionMensaje() {
        // Arrange
        Carro carro = new CarroTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 04, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result)
                .expectErrorMessage(RegistrarCarro.getParaLasPlacasQueEmpiecenPorASoloPuedenIngresarLosLunesYDomingos())
                .verify();
    }


    @Test
    public void ejecutarExitosoCuandoSiExistePlacaRegistradaValidarExcepcion() {
        // Arrange
        Carro carro = new CarroTestDataBuilder().build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

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
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(12)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectErrorMessage(RegistrarCarro.getElCarroAunNoSeLiquida()).verify();
    }


    @Test
    public void ejecutarExitosoCuandoCantidadIngresadaValidarExcepcion() {
        // Arrange
        Carro carro = new CarroTestDataBuilder().build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(21)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectError(ExcepcionMaximoCupos.class).verify();
    }

    @Test
    public void ejecutarExitosoCuandoCantidadIngresadaValidarExcepcionMensaje() {
        // Arrange
        Carro carro = new CarroTestDataBuilder().build();

        IngresarCarro ingresarCarro = Mockito.mock(IngresarCarro.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadCarro cantidadCarro = Mockito.mock(CantidadCarro.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarCarro registrarCarro = new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarCarro.ingresarCarro(any(Carro.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadCarro.cantidadCarro())
                .thenReturn(Mono.just(Long.valueOf(21)));


        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Carro> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result)
                .expectErrorMessage(RegistrarCarro.getCupoMaximoCarrosOcupado())
                .verify();
    }


}
