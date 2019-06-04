package co.com.parqueadero.validaciones.integracion;


import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.modelo.CarroData;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import co.com.parqueadero.validaciones.DTOTestBuilder.CarroDTOTestBuilder;
import co.com.parqueadero.validaciones.DTOTestBuilder.RegistroCarroDataTestDataBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrarCarroTestIntegracion {


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;


    @Test
    public void registrarCarroComparacionBaseDeDatos() {

        //Arrange
        CarroDTO carroDTO = new CarroDTOTestBuilder().build();

        Registro<CarroData> carroRegistro = new RegistroCarroDataTestDataBuilder().build();

        Query query = Query.query(Criteria.where(Constantes.REGISTRO_PLACA).is(carroDTO.getPlaca()));

        //Act
        webTestClient.post().uri("/carros").syncBody(carroDTO).exchange();
        Registro<CarroData> resultado = this.reactiveMongoOperations.findOne(query, Registro.class).block();


        //Assert
        Assert.assertEquals(resultado.getTipo(), carroRegistro.getTipo());
        Assert.assertEquals(resultado.getRegisto(), carroRegistro.getRegisto());
        Assert.assertEquals(resultado.getValorDia(), carroRegistro.getValorDia());
        Assert.assertEquals(resultado.getValorHora(), carroRegistro.getValorHora());
    }


    @Test
    public void registrarCarroRespuestaRest() {

        //Arrange
        CarroDTO carroDTO = new CarroDTOTestBuilder()
                .conPlaca("ppp-888")
                .build();

        Registro<CarroData> carroRegistro = new RegistroCarroDataTestDataBuilder().build();

        //Act
        WebTestClient.ResponseSpec resultadoCarroRespuestaRest = webTestClient
                .post()
                .uri("/carros")
                .syncBody(carroDTO)
                .exchange();

        //Assert
        resultadoCarroRespuestaRest
                .expectBody()
                .jsonPath("$.placa").isEqualTo("ppp-888")
                .jsonPath("$.valorHora").isEqualTo(carroRegistro.getValorHora())
                .jsonPath("$.valorDia").isEqualTo(carroRegistro.getValorDia())
                .jsonPath("$.fechaEntrada").isNotEmpty();


    }



}
