package co.com.parqueadero.servicios;

import co.com.parqueadero.TestDataBuilder.MotoTestDataBuilder;
import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.ConsultarMoto;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.SalidaMoto;
import co.com.parqueadero.core.servicios.LiquidacionMoto;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class LiquidacionMotoTest {


    @Test
    public void cuandoSeLiquidaCorrectamenteMenorDeNueveHoras() {
        // Arrange

        Moto motoSalida = new MotoTestDataBuilder()
                .conFechaSalida(LocalDateTime.of(1992, 10, 20, 12, 20))
                .build();

        Moto motoConsulta = new MotoTestDataBuilder().build();

        Tuple2<Moto, BigInteger> valorFinal = Tuples.of(motoSalida, BigInteger.valueOf(2000));

        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaMoto salidaMoto = Mockito.mock(SalidaMoto.class);
        ConsultarMoto consultarMoto = Mockito.mock(ConsultarMoto.class);

        LiquidacionMoto liquidacionMoto = new LiquidacionMoto(existenciaVehiculo, salidaMoto, consultarMoto);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));


        when(salidaMoto.salidaMoto(anyString(), any()))
                .thenReturn(Mono.just(motoSalida));

        when(consultarMoto.consultarMoto(anyString()))
                .thenReturn(Mono.just(motoConsulta));


        //Act
        Mono<Tuple2<Moto, BigInteger>> result = liquidacionMoto.darSalidaMoto("cualquier placa");


        //Assert

        StepVerifier.create(result).expectNext(valorFinal).verifyComplete();


    }

    @Test
    public void cuandoSeLiquidaCorrectamenteCilindrajeMayorDe500() {
        // Arrange

        Moto motoSalida = new MotoTestDataBuilder()
                .conCilindraje(501)
                .conFechaSalida(LocalDateTime.of(1992, 10, 20, 12, 20))
                .build();

        Moto motoConsulta = new MotoTestDataBuilder()
                .conCilindraje(501)
                .build();

        Tuple2<Moto, BigInteger> valorFinal = Tuples.of(motoSalida, BigInteger.valueOf(4000));

        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaMoto salidaMoto = Mockito.mock(SalidaMoto.class);
        ConsultarMoto consultarMoto = Mockito.mock(ConsultarMoto.class);

        LiquidacionMoto liquidacionMoto = new LiquidacionMoto(existenciaVehiculo, salidaMoto, consultarMoto);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));


        when(salidaMoto.salidaMoto(anyString(), any()))
                .thenReturn(Mono.just(motoSalida));

        when(consultarMoto.consultarMoto(anyString()))
                .thenReturn(Mono.just(motoConsulta));


        //Act
        Mono<Tuple2<Moto, BigInteger>> result = liquidacionMoto.darSalidaMoto("cualquier placa");


        //Assert

        StepVerifier.create(result).expectNext(valorFinal).verifyComplete();


    }

    @Test
    public void cuandoSeLiquidaCorrectamenteMayor24Horas() {
        // Arrange
        Moto motoSalida = new MotoTestDataBuilder()
                .conFechaSalida(LocalDateTime.of(1992, 10, 22, 21, 20))
                .build();

        Moto motoConsulta = new MotoTestDataBuilder().build();

        Tuple2<Moto, BigInteger> valorFinal = Tuples.of(motoSalida, BigInteger.valueOf(27000));

        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaMoto salidaMoto = Mockito.mock(SalidaMoto.class);
        ConsultarMoto consultarMoto = Mockito.mock(ConsultarMoto.class);

        LiquidacionMoto liquidacionMoto = new LiquidacionMoto(existenciaVehiculo, salidaMoto, consultarMoto);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));


        when(salidaMoto.salidaMoto(anyString(), any()))
                .thenReturn(Mono.just(motoSalida));

        when(consultarMoto.consultarMoto(anyString()))
                .thenReturn(Mono.just(motoConsulta));


        //Act
        Mono<Tuple2<Moto, BigInteger>> result = liquidacionMoto.darSalidaMoto("cualquier placa");


        //Assert
        StepVerifier.create(result).expectNext(valorFinal).verifyComplete();

    }

    @Test
    public void cuandoNoExisteVehiculoALiquidar() {
        // Arrange
        Moto motoSalida = new MotoTestDataBuilder()
                .conFechaSalida(LocalDateTime.of(1992, 10, 22, 21, 20))
                .build();

        Moto motoConsulta = new MotoTestDataBuilder().build();


        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaMoto salidaMoto = Mockito.mock(SalidaMoto.class);
        ConsultarMoto consultarMoto = Mockito.mock(ConsultarMoto.class);

        LiquidacionMoto liquidacionMoto = new LiquidacionMoto(existenciaVehiculo, salidaMoto, consultarMoto);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));


        when(salidaMoto.salidaMoto(anyString(), any()))
                .thenReturn(Mono.just(motoSalida));

        when(consultarMoto.consultarMoto(anyString()))
                .thenReturn(Mono.just(motoConsulta));


        //Act
        Mono<Tuple2<Moto, BigInteger>> result = liquidacionMoto.darSalidaMoto("cualquier placa");


        //Assert
        StepVerifier.create(result).expectError(ExcepcionDuplicidad.class).verify();

    }



    @Test
    public void cuandoNoExisteVehiculoALiquidarMensaje() {
        // Arrange
        Moto motoSalida = new MotoTestDataBuilder()
                .conFechaSalida(LocalDateTime.of(1992, 10, 22, 21, 20))
                .build();

        Moto motoConsulta = new MotoTestDataBuilder().build();


        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaMoto salidaMoto = Mockito.mock(SalidaMoto.class);
        ConsultarMoto consultarMoto = Mockito.mock(ConsultarMoto.class);

        LiquidacionMoto liquidacionMoto = new LiquidacionMoto(existenciaVehiculo, salidaMoto, consultarMoto);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));


        when(salidaMoto.salidaMoto(anyString(), any()))
                .thenReturn(Mono.just(motoSalida));

        when(consultarMoto.consultarMoto(anyString()))
                .thenReturn(Mono.just(motoConsulta));


        //Act
        Mono<Tuple2<Moto, BigInteger>> result = liquidacionMoto.darSalidaMoto("cualquier placa");


        //Assert
        StepVerifier.create(result).expectErrorMessage(liquidacionMoto.getLaMotoNoSeHaIngresado()).verify();

    }




}
