package co.com.parqueadero.validaciones.integracion;


import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrarMotoTestIntegracion {


    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void registrarMoto(){
        MotoDTO motoDTO = new MotoDTO(
                "ABC - abc - 123",
                null,
                null,
                null,
                null,
                150);


        webTestClient.post().uri("/motos").syncBody(motoDTO).exchange();

    }


}
