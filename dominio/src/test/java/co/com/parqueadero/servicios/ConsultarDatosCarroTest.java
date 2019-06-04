package co.com.parqueadero.servicios;

import co.com.parqueadero.TestDataBuilder.CarroTestDataBuilder;
import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.ConsultarCarro;
import co.com.parqueadero.core.servicios.ConsultarDatosCarro;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ConsultarDatosCarroTest {


    @Test
    public void consultarDatosCarroExitoso() {
        // Arrange

        Carro carro = new CarroTestDataBuilder().build();

        ConsultarCarro consultarCarro = Mockito.mock(ConsultarCarro.class);

        ConsultarDatosCarro consultarDatosCarro = new ConsultarDatosCarro(consultarCarro);

        when(consultarCarro.consultarCarro(anyString()))
                .thenReturn(Mono.just(carro));


        //Act
        Mono<Carro> result = consultarDatosCarro.ejecutar("cualquier placa");


        //Assert
        StepVerifier.create(result).expectNext(carro).verifyComplete();


    }


}
