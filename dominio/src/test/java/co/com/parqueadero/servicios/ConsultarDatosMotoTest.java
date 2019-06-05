package co.com.parqueadero.servicios;

import co.com.parqueadero.TestDataBuilder.MotoTestDataBuilder;
import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.ConsultarMoto;
import co.com.parqueadero.core.servicios.ConsultarDatosMoto;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ConsultarDatosMotoTest {

    @Test
    public void consultarDatosMotoExitoso() {
        // Arrange

        Moto moto = new MotoTestDataBuilder().build();

        ConsultarMoto consultarMoto = Mockito.mock(ConsultarMoto.class);

        ConsultarDatosMoto consultarDatosCarro = new ConsultarDatosMoto(consultarMoto);

        when(consultarMoto.consultarMoto(anyString()))
                .thenReturn(Mono.just(moto));


        //Act
        Mono<Moto> result = consultarDatosCarro.ejecutar("cualquier placa");


        //Assert
        StepVerifier.create(result).expectNext(moto).verifyComplete();


    }

}
