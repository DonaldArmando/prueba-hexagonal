package co.com.parqueadero.servicios;

import co.com.parqueadero.TestDataBuilder.MotoTestDataBuilder;
import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.CantidadMoto;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarMoto;
import co.com.parqueadero.core.servicios.RegistrarMoto;
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

public class RegistrarMotoTest {


    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistrada() {
        // Arrange
        Moto moto = new MotoTestDataBuilder().build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = Mockito.mock(UtilidadFecha.class);
        RegistrarMoto registrarMoto = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(9)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Moto> result = registrarMoto.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectNext(moto).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoSiExistePlacaRegistradaValidarExcepcion() {
        // Arrange
        Moto moto = new MotoTestDataBuilder().build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = Mockito.mock(UtilidadFecha.class);
        RegistrarMoto registrarMoto = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(9)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Moto> result = registrarMoto.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectError(ExcepcionDuplicidad.class).verify();
    }

    @Test
    public void ejecutarExitosoCuandoSiExistePlacaRegistradaValidarExcepcionMensaje() {
        // Arrange
        Moto moto = new MotoTestDataBuilder().build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = Mockito.mock(UtilidadFecha.class);
        RegistrarMoto registrarMoto = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(9)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Moto> result = registrarMoto.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectErrorMessage(RegistrarMoto.getLaMotoAunNoSeLiquida()).verify();
    }


    @Test
    public void ejecutarExitosoCuandoSiMaximoCuposValidarExcepcion() {
        // Arrange
        Moto moto = new MotoTestDataBuilder().build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = Mockito.mock(UtilidadFecha.class);
        RegistrarMoto registrarMoto = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(11)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Moto> result = registrarMoto.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectError(ExcepcionMaximoCupos.class).verify();
    }

    @Test
    public void ejecutarExitosoCuandoSiMaximoCuposValidarExcepcionMensaje() {
        // Arrange
        Moto moto = new MotoTestDataBuilder().build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = Mockito.mock(UtilidadFecha.class);
        RegistrarMoto registrarMoto = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(11)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Moto> result = registrarMoto.ejecutar(moto);

        //Assert
        StepVerifier.create(result)
                .expectErrorMessage(RegistrarMoto.getCupoMaximoMotosOcupado())
                .verify();
    }


    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYEsDomingo() {
        // Arrange
        Moto moto = new MotoTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarMoto registrarMoto = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(2)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 02, 10, 10));

        // Act
        Mono<Moto> result = registrarMoto.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectNext(moto).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYEsDomingoYminusculaLaPrimeraLetra() {
        // Arrange
        Moto carro = new MotoTestDataBuilder()
                .conPlaca("abc-123")
                .build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarMoto registrarMoto = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(2)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 02, 10, 10));

        // Act
        Mono<Moto> result = registrarMoto.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYNoEsNiLunesNiDomingo() {
        // Arrange
        Moto moto = new MotoTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarMoto registrarCarro = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(9)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 04, 10, 10));

        // Act
        Mono<Moto> result = registrarCarro.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectError(ExcepcionDiaSemana.class).verify();
    }


    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYEsLunes() {
        // Arrange
        Moto moto = new MotoTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadmoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarMoto registrarCarro = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadmoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadmoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(2)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 03, 10, 10));

        // Act
        Mono<Moto> result = registrarCarro.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectNext(moto).verifyComplete();
    }


    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYNoEsLunesExcepcion() {
        // Arrange
        Moto moto = new MotoTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarMoto registrarCarro = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(moto));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(2)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 04, 10, 10));

        // Act
        Mono<Moto> result = registrarCarro.ejecutar(moto);

        //Assert
        StepVerifier.create(result).expectError(ExcepcionDiaSemana.class).verify();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaDiferenteDeAYNoEsNiDomingoNiLunes() {
        // Arrange
        Moto carro = new MotoTestDataBuilder()
                .conPlaca("CBA-123")
                .build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarMoto registrarCarro = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(2)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 04, 10, 10));

        // Act
        Mono<Moto> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();
    }

    @Test
    public void ejecutarExitosoCuandoNoExistePlacaRegistradaYLaPlacaEmpiezaPorAYNoEsLunesExcepcionMensaje() {
        // Arrange
        Moto carro = new MotoTestDataBuilder()
                .conPlaca("ABC-123")
                .build();

        IngresarMoto ingresarMoto = Mockito.mock(IngresarMoto.class);
        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        CantidadMoto cantidadMoto = Mockito.mock(CantidadMoto.class);
        UtilidadFecha utilidadFecha = mock(UtilidadFecha.class);

        RegistrarMoto registrarCarro = new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));

        when(ingresarMoto.ingresarMoto(any(Moto.class)))
                .thenReturn(Mono.just(carro));

        when(cantidadMoto.cantidadMoto())
                .thenReturn(Mono.just(Long.valueOf(2)));

        when(utilidadFecha.fechaActual())
                .thenReturn(LocalDateTime.of(2019, 06, 04, 10, 10));

        // Act
        Mono<Moto> result = registrarCarro.ejecutar(carro);

        //Assert
        StepVerifier.create(result)
                .expectErrorMessage(RegistrarMoto.getParaLasPlacasQueEmpiecenPorASoloPuedenIngresarLosLunesYDomingos())
                .verify();
    }


}
