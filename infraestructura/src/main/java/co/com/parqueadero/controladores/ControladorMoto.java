package co.com.parqueadero.controladores;


import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;
import co.com.parqueadero.manejador.moto.ManejadorIngresarMoto;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path = "/motos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControladorMoto {

    private final ManejadorIngresarMoto manejadorIngresarMoto;

    public ControladorMoto(ManejadorIngresarMoto manejadorIngresarMoto) {
        this.manejadorIngresarMoto = manejadorIngresarMoto;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Moto> registrarMoto(@RequestBody MotoDTO motoDTO) {
        return this.manejadorIngresarMoto.ejecutar(motoDTO);
    }

}
