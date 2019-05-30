package co.com.parqueadero.controladores;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.manejador.carro.ManejadorIngresarCarro;
import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path = "/carros", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControladorCarro {

    private ManejadorIngresarCarro manejadorIngresarCarro;

    public ControladorCarro(ManejadorIngresarCarro manejadorIngresarCarro) {
        this.manejadorIngresarCarro = manejadorIngresarCarro;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Carro> registrarCarro(@RequestBody CarroDTO carroDTO) {
        return this.manejadorIngresarCarro.ejecutar(carroDTO);
    }


}
