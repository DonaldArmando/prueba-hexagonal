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

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalidaMotoTestIntegracion {


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;


    @Test
    public void salidaMotoValidacionRest() {
        //Arrange
        Registro<MotoData> motoRegistro = new RegistroMotoDataTestDataBuilder()
                .conRegistro(new MotoData(null, "mnb-369", null, null, null, null,125))
                .conFechaEntrada(LocalDateTime.now().minusMinutes(30))
                .build();

        this.reactiveMongoOperations.save(motoRegistro).block();

        //Act
        WebTestClient.ResponseSpec respuesta = webTestClient
                .get()
                .uri("/motos/mnb-369/salida")
                .exchange();

        //Assert
        respuesta
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.registro.placa").isEqualTo(motoRegistro.getRegisto().getPlaca())
                .jsonPath("$.registro.valorHora").isEqualTo(motoRegistro.getValorHora())
                .jsonPath("$.registro.valorDia").isEqualTo(motoRegistro.getValorDia())
                .jsonPath("$.registro.cilindraje").isEqualTo(motoRegistro.getRegisto().getCilindraje())
                .jsonPath("$.registro.fechaEntrada").isNotEmpty()
                .jsonPath("$.valorSalida").isEqualTo(motoRegistro.getValorHora());
    }


}
