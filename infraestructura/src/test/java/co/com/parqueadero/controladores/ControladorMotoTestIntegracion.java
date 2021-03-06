package co.com.parqueadero.controladores;

import co.com.parqueadero.TestDataBuilder.MotoTestDataBuilder;
import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;
import co.com.parqueadero.manejador.moto.ManejadorConsultarMoto;
import co.com.parqueadero.manejador.moto.ManejadorIngresarMoto;
import co.com.parqueadero.manejador.moto.ManejadorSalidaMoto;
import co.com.parqueadero.testDataBuilder.MotoDTOTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class ControladorMotoTestIntegracion {


    @Test
    public void registrarVehiculoIntegracionMoto() {

        //Arrange
        MotoDTO motoDTO = new MotoDTOTestDataBuilder().build();
        Moto moto = new MotoTestDataBuilder().build();

        ManejadorIngresarMoto manejadorIngresarMoto = Mockito.mock(ManejadorIngresarMoto.class);
        ManejadorConsultarMoto manejadorConsultarMoto = Mockito.mock(ManejadorConsultarMoto.class);
        ManejadorSalidaMoto manejadorSalidaMoto = Mockito.mock(ManejadorSalidaMoto.class);


        when(manejadorIngresarMoto.ejecutar(any(MotoDTO.class))).thenReturn(Mono.just(moto));

        WebTestClient webTestClient = WebTestClient
                .bindToController(new ControladorMoto(
                        manejadorIngresarMoto,
                        manejadorConsultarMoto,
                        manejadorSalidaMoto
                ))
                .build();


        //Act
        WebTestClient.ResponseSpec spect = webTestClient
                .post()
                .uri("/motos")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(motoDTO)
                .exchange();


        //Assert
        spect
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("placa")
                .isEqualTo(moto.getPlaca());
    }

}
