package co.com.parqueadero.servicios;

import co.com.parqueadero.TestDataBuilder.CarroTestDataBuilder;
import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.ConsultarCarro;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.SalidaCarro;
import co.com.parqueadero.core.servicios.LiquidacionCarro;
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

public class LiquidacionCarroTest {


    @Test
    public void cuandoSeLiquidaCorrectamenteMenorDeNueveHoras() {
        // Arrange

        Carro carroSalida = new CarroTestDataBuilder()
                .conFechaSalida(LocalDateTime.of(1992, 10, 20, 12, 20))
                .build();

        Carro carroConsulta = new CarroTestDataBuilder().build();

        Tuple2<Carro, BigInteger> valorFinal = Tuples.of(carroSalida, BigInteger.valueOf(2000));

        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaCarro salidaCarro = Mockito.mock(SalidaCarro.class);
        ConsultarCarro consultarCarro = Mockito.mock(ConsultarCarro.class);

        LiquidacionCarro liquidacionCarro = new LiquidacionCarro(existenciaVehiculo, salidaCarro, consultarCarro);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));


        when(salidaCarro.salidaCarro(anyString(), any()))
                .thenReturn(Mono.just(carroSalida));

        when(consultarCarro.consultarCarro(anyString()))
                .thenReturn(Mono.just(carroConsulta));


        //Act
        Mono<Tuple2<Carro, BigInteger>> result = liquidacionCarro.darSalidaCarro("cualquier placa");


        //Assert

        StepVerifier.create(result).expectNext(valorFinal).verifyComplete();


    }

    @Test
    public void cuandoSeLiquidaCorrectamenteMayor24Horas() {
        // Arrange
        Carro carroSalida = new CarroTestDataBuilder()
                .conFechaSalida(LocalDateTime.of(1992, 10, 22, 21, 20))
                .build();

        Carro carroConsulta = new CarroTestDataBuilder().build();

        Tuple2<Carro, BigInteger> valorFinal = Tuples.of(carroSalida, BigInteger.valueOf(27000));

        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaCarro salidaCarro = Mockito.mock(SalidaCarro.class);
        ConsultarCarro consultarCarro = Mockito.mock(ConsultarCarro.class);

        LiquidacionCarro liquidacionCarro = new LiquidacionCarro(existenciaVehiculo, salidaCarro, consultarCarro);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.TRUE));


        when(salidaCarro.salidaCarro(anyString(), any()))
                .thenReturn(Mono.just(carroSalida));

        when(consultarCarro.consultarCarro(anyString()))
                .thenReturn(Mono.just(carroConsulta));


        //Act
        Mono<Tuple2<Carro, BigInteger>> result = liquidacionCarro.darSalidaCarro("cualquier placa");


        //Assert
        StepVerifier.create(result).expectNext(valorFinal).verifyComplete();

    }

    @Test
    public void cuandoNoExisteVehiculoALiquidar() {
        // Arrange
        Carro carroSalida = new CarroTestDataBuilder()
                .conFechaSalida(LocalDateTime.of(1992, 10, 22, 21, 20))
                .build();

        Carro carroConsulta = new CarroTestDataBuilder().build();


        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaCarro salidaCarro = Mockito.mock(SalidaCarro.class);
        ConsultarCarro consultarCarro = Mockito.mock(ConsultarCarro.class);

        LiquidacionCarro liquidacionCarro = new LiquidacionCarro(existenciaVehiculo, salidaCarro, consultarCarro);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));


        when(salidaCarro.salidaCarro(anyString(), any()))
                .thenReturn(Mono.just(carroSalida));

        when(consultarCarro.consultarCarro(anyString()))
                .thenReturn(Mono.just(carroConsulta));


        //Act
        Mono<Tuple2<Carro, BigInteger>> result = liquidacionCarro.darSalidaCarro("cualquier placa");


        //Assert
        StepVerifier.create(result).expectError(ExcepcionDuplicidad.class).verify();

    }



    @Test
    public void cuandoNoExisteVehiculoALiquidarMensaje() {
        // Arrange
        Carro carroSalida = new CarroTestDataBuilder()
                .conFechaSalida(LocalDateTime.of(1992, 10, 22, 21, 20))
                .build();

        Carro carroConsulta = new CarroTestDataBuilder().build();


        ExistenciaVehiculo existenciaVehiculo = Mockito.mock(ExistenciaVehiculo.class);
        SalidaCarro salidaCarro = Mockito.mock(SalidaCarro.class);
        ConsultarCarro consultarCarro = Mockito.mock(ConsultarCarro.class);

        LiquidacionCarro liquidacionCarro = new LiquidacionCarro(existenciaVehiculo, salidaCarro, consultarCarro);

        when(existenciaVehiculo.existenciaVehiculo(anyString()))
                .thenReturn(Mono.just(Boolean.FALSE));


        when(salidaCarro.salidaCarro(anyString(), any()))
                .thenReturn(Mono.just(carroSalida));

        when(consultarCarro.consultarCarro(anyString()))
                .thenReturn(Mono.just(carroConsulta));


        //Act
        Mono<Tuple2<Carro, BigInteger>> result = liquidacionCarro.darSalidaCarro("cualquier placa");


        //Assert
        StepVerifier.create(result).expectErrorMessage(LiquidacionCarro.getElCarroNoSeHaIngresado()).verify();

    }




}
