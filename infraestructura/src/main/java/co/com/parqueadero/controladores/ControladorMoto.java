package co.com.parqueadero.controladores;

import co.com.parqueadero.DTO.MotoDTO;
import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.manejador.moto.ManejadorIngresarMoto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@RestController
@RequestMapping(path = "/motos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControladorMoto {

    private final ManejadorIngresarMoto manejadorIngresarMoto;

    public ControladorMoto(ManejadorIngresarMoto manejadorIngresarMoto) {
        this.manejadorIngresarMoto = manejadorIngresarMoto;
    }

  /*  @PostMapping
    public Mono<MotoDTO> registrarMoto(@RequestBody MotoDTO motoDTO) {
        Moto moto = new Moto(
                motoDTO.getPlaca(),
                motoDTO.getFechaEntrada(),
                motoDTO.getFechaSalida(),
                motoDTO.getValorHora(),
                motoDTO.getValorDia(),
                motoDTO.getCilindraje());
        return this.manejadorIngresarMoto.ejecutar(moto)
                .map(motoDominio -> new MotoDTO(
                        motoDTO.getPlaca(),
                        motoDTO.getFechaEntrada(),
                        motoDTO.getFechaSalida(),
                        motoDTO.getValorHora(),
                        motoDTO.getValorDia(),
                        motoDTO.getCilindraje()));
    }*/

    @GetMapping
    public Mono<Moto> a() {
        return this.manejadorIngresarMoto.ejecutar(new Moto("Placa", LocalDateTime.now(), LocalDateTime.now(), 1, 1, 1));
    }

}
