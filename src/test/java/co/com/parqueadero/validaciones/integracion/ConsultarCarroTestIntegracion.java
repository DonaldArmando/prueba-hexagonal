package co.com.parqueadero.validaciones.integracion;


import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
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


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsultarCarroTestIntegracion {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;

    @Test
    public void consultarCarroComparacionRespuestaRest() {

        //Arrange
        Registro<CarroData> carroRegistro = new RegistroCarroDataTestDataBuilder().build();

        this.reactiveMongoOperations.save(carroRegistro).block();


        //Act
        WebTestClient.ResponseSpec respuesta = webTestClient
                .get()
                .uri("/carros/jjj-333")
                .exchange();


        //Assert
        respuesta
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.placa").isEqualTo(carroRegistro.getRegisto().getPlaca())
                .jsonPath("$.valorHora").isEqualTo(carroRegistro.getValorHora())
                .jsonPath("$.valorDia").isEqualTo(carroRegistro.getValorDia())
                .jsonPath("$.fechaEntrada").isNotEmpty();

    }


}
