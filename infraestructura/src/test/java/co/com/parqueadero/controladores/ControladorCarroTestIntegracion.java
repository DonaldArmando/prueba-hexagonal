package co.com.parqueadero.controladores;


import co.com.parqueadero.TestDataBuilder.CarroTestDataBuilder;
import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.manejador.carro.ManejadorConsultarCarro;
import co.com.parqueadero.manejador.carro.ManejadorIngresarCarro;
import co.com.parqueadero.manejador.carro.ManejadorSalidaCarro;
import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;
import co.com.parqueadero.testDataBuilder.CarroDTOTestDataBuilder;
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
public class ControladorCarroTestIntegracion {

    @Test
    public void registrarVehiculoIntegracionCarro() {

        //Arrange
        CarroDTO carroDTO = new CarroDTOTestDataBuilder().build();
        Carro carro = new CarroTestDataBuilder().build();

        ManejadorIngresarCarro manejadorIngresarCarro = Mockito.mock(ManejadorIngresarCarro.class);
        ManejadorConsultarCarro manejadorConsultarCarro = Mockito.mock(ManejadorConsultarCarro.class);
        ManejadorSalidaCarro manejadorSalidaCarro = Mockito.mock(ManejadorSalidaCarro.class);

        when(manejadorIngresarCarro.ejecutar(any(CarroDTO.class))).thenReturn(Mono.just(carro));

        WebTestClient webTestClient = WebTestClient
                .bindToController(new ControladorCarro(
                        manejadorIngresarCarro,
                        manejadorConsultarCarro,
                        manejadorSalidaCarro
                ))
                .build();


        //Act
        WebTestClient.ResponseSpec spect = webTestClient
                .post()
                .uri("/carros")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(carroDTO)
                .exchange();


        //Assert
        spect
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("placa")
                .isEqualTo(carro.getPlaca());
    }


}
