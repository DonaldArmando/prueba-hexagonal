package co.com.parqueadero.validaciones.integracion;


import co.com.parqueadero.repositorio.mongodb.modelo.CarroData;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import co.com.parqueadero.validaciones.DTOTestBuilder.RegistroCarroDataTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalidaCarroTestIntegracion {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;


    @Test
    public void salidaCarroValidacionRest() {
        //Arrange
        Registro<CarroData> carroRegistro = new RegistroCarroDataTestDataBuilder()
                .conRegistro(new CarroData(null, "QWE-987", null, null, null, null))
                .conFechaEntrada(LocalDateTime.now().minusMinutes(30))
                .build();

        this.reactiveMongoOperations.save(carroRegistro).block();

        //Act
        WebTestClient.ResponseSpec respuesta = webTestClient
                .get()
                .uri("/carros/QWE-987/salida")
                .exchange();

        //Assert
        respuesta
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.registro.placa").isEqualTo(carroRegistro.getRegisto().getPlaca())
                .jsonPath("$.registro.valorHora").isEqualTo(carroRegistro.getValorHora())
                .jsonPath("$.registro.valorDia").isEqualTo(carroRegistro.getValorDia())
                .jsonPath("$.registro.fechaEntrada").isNotEmpty()
                .jsonPath("$.valorSalida").isEqualTo(carroRegistro.getValorHora());
    }


}
