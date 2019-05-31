package co.com.parqueadero.controladores;


import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;
import co.com.parqueadero.manejador.fabrica.dto.SalidaDTO;
import co.com.parqueadero.manejador.moto.ManejadorConsultarMoto;
import co.com.parqueadero.manejador.moto.ManejadorIngresarMoto;

import co.com.parqueadero.manejador.moto.ManejadorSalidaMoto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigInteger;


@RestController
@RequestMapping(path = "/motos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControladorMoto {

    private final ManejadorIngresarMoto manejadorIngresarMoto;
    private final ManejadorConsultarMoto manejadorConsultarMoto;
    private final ManejadorSalidaMoto manejadorSalidaMoto;

    public ControladorMoto(
            ManejadorIngresarMoto manejadorIngresarMoto,
            ManejadorConsultarMoto manejadorConsultarMoto,
            ManejadorSalidaMoto manejadorSalidaMoto
    ) {
        this.manejadorIngresarMoto = manejadorIngresarMoto;
        this.manejadorConsultarMoto = manejadorConsultarMoto;
        this.manejadorSalidaMoto = manejadorSalidaMoto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Moto> registrarMoto(@RequestBody MotoDTO motoDTO) {
        return this.manejadorIngresarMoto.ejecutar(motoDTO);
    }


    @GetMapping(path = "/{placa}")
    public Mono<Moto> consultarMoto(@PathVariable String placa) {
        return this.manejadorConsultarMoto.ejecutar(placa);
    }

    @GetMapping(path = "/{placa}/salida")
    public Mono<SalidaDTO> sacarMoto(@PathVariable String placa) {
        return Mono.just(new SalidaDTO(placa, BigInteger.ONE)); //this.manejadorSalidaMoto.ejecutar(placa);
    }


}
