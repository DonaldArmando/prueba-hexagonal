package co.com.parqueadero.manejador.carro;

import co.com.parqueadero.core.servicios.LiquidacionCarro;
import co.com.parqueadero.manejador.fabrica.SalidaDTOConvertidor;
import co.com.parqueadero.manejador.fabrica.dto.SalidaDTO;
import reactor.core.publisher.Mono;

public class ManejadorSalidaCarro {

    private final LiquidacionCarro liquidacionCarro;
    private final SalidaDTOConvertidor salidaDTOConvertidor;

    public ManejadorSalidaCarro(
            LiquidacionCarro liquidacionCarro,
            SalidaDTOConvertidor salidaDTOConvertidor
    ) {
        this.liquidacionCarro = liquidacionCarro;
        this.salidaDTOConvertidor = salidaDTOConvertidor;
    }

    public Mono<SalidaDTO> ejecutar(String placa) {
        return this.liquidacionCarro.darSalidaCarro(placa)
                .map(carroSalida -> salidaDTOConvertidor
                        .salidaDTO(carroSalida.getT1(), carroSalida.getT2())
                );
    }

}
