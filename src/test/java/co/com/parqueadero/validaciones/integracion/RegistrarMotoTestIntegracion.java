package co.com.parqueadero.validaciones.integracion;


import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;
import co.com.parqueadero.repositorio.mongodb.enums.Constantes;
import co.com.parqueadero.repositorio.mongodb.modelo.MotoData;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;
import co.com.parqueadero.validaciones.DTOTestBuilder.MotoDTOTestBuilder;
import co.com.parqueadero.validaciones.DTOTestBuilder.RegistroMotoDataTestDataBuilder;
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
public class RegistrarMotoTestIntegracion {


    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;

    @Test
    public void registrarMotoComparacionBaseDeDatos() {
        MotoDTO motoDTO = new MotoDTOTestBuilder().build();

        Registro<MotoData> motoRegistro = new RegistroMotoDataTestDataBuilder().build();


        webTestClient.post().uri("/motos").syncBody(motoDTO).exchange();

        Query query = Query.query(Criteria.where(Constantes.REGISTRO_PLACA).is(motoDTO.getPlaca()));


        Registro<MotoData> resultado = this.reactiveMongoOperations.findOne(query, Registro.class).block();

        Assert.assertEquals(resultado.getTipo(), motoRegistro.getTipo());
        Assert.assertEquals(resultado.getRegisto(), motoRegistro.getRegisto());
        Assert.assertEquals(resultado.getValorDia(), motoRegistro.getValorDia());
        Assert.assertEquals(resultado.getValorHora(), motoRegistro.getValorHora());


    }


    @Test
    public void registrarMotoRespuestaRest() {
        MotoDTO motoDTO = new MotoDTOTestBuilder().build();

        Registro<MotoData> motoRegistro = new RegistroMotoDataTestDataBuilder().build();


        webTestClient.post().uri("/motos")
                .syncBody(motoDTO)
                .exchange()
                .expectBody()
                .jsonPath("$.placa").isEqualTo(motoRegistro.getRegisto().getPlaca())
                .jsonPath("$.cilindraje").isEqualTo(motoRegistro.getRegisto().getCilindraje())
                .jsonPath("$.valorHora").isEqualTo(motoRegistro.getValorHora())
                .jsonPath("$.valorDia").isEqualTo(motoRegistro.getValorDia())
                .jsonPath("$.fechaEntrada").isNotEmpty();


    }


}
