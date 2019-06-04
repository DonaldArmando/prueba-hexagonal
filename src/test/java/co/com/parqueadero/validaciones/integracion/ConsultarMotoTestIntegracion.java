package co.com.parqueadero.validaciones.integracion;


import co.com.parqueadero.repositorio.mongodb.modelo.MotoData;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import co.com.parqueadero.validaciones.DTOTestBuilder.RegistroMotoDataTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsultarMotoTestIntegracion {


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;


    @Test
    public void consultarCarroComparacionRespuestaRest() {

        //Arrange
        Registro<MotoData> motoRegistro = new RegistroMotoDataTestDataBuilder().build();

        this.reactiveMongoOperations.save(motoRegistro).block();


        //Act
        WebTestClient.ResponseSpec respuesta = webTestClient
                .get()
                .uri("/motos/BBC-123")
                .exchange();


        //Assert
        respuesta
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.placa").isEqualTo(motoRegistro.getRegisto().getPlaca())
                .jsonPath("$.valorHora").isEqualTo(motoRegistro.getValorHora())
                .jsonPath("$.valorDia").isEqualTo(motoRegistro.getValorDia())
                .jsonPath("$.cilindraje").isEqualTo(motoRegistro.getRegisto().getCilindraje())
                .jsonPath("$.fechaEntrada").isNotEmpty();

    }


}
