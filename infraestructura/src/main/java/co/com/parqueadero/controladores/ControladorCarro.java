package co.com.parqueadero.controladores;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.manejador.carro.ManejadorConsultarCarro;
import co.com.parqueadero.manejador.carro.ManejadorIngresarCarro;
import co.com.parqueadero.manejador.carro.ManejadorSalidaCarro;
import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;
import co.com.parqueadero.manejador.fabrica.dto.SalidaDTO;
import co.com.parqueadero.repositorio.mongodb.modelo.VehiculoData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigInteger;


@RestController
@RequestMapping(path = "/carros")
@CrossOrigin(allowCredentials = "*")
public class ControladorCarro {

    private final ManejadorIngresarCarro manejadorIngresarCarro;
    private final ManejadorConsultarCarro manejadorConsultarCarro;
    private final ManejadorSalidaCarro manejadorSalidaCarro;

    public ControladorCarro(
            ManejadorIngresarCarro manejadorIngresarCarro,
            ManejadorConsultarCarro manejadorConsultarCarro,
            ManejadorSalidaCarro manejadorSalidaCarro
    ) {
        this.manejadorIngresarCarro = manejadorIngresarCarro;
        this.manejadorConsultarCarro = manejadorConsultarCarro;
        this.manejadorSalidaCarro = manejadorSalidaCarro;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Carro> registrarCarro(@RequestBody CarroDTO carroDTO) {
        return this.manejadorIngresarCarro.ejecutar(carroDTO);
    }


    @GetMapping(path = "/{placa}")
    public Mono<Carro> consultarCarro(@PathVariable String placa) {
        return this.manejadorConsultarCarro.ejecutar(placa);
    }

    @GetMapping(path = "/{placa}/salida")
    public Mono<SalidaDTO> sacarCarro(@PathVariable String placa) {
        return this.manejadorSalidaCarro.ejecutar(placa);
    }

}
