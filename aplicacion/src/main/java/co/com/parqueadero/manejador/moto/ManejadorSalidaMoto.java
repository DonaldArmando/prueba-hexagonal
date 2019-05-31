package co.com.parqueadero.manejador.moto;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.servicios.LiquidacionMoto;
import co.com.parqueadero.manejador.fabrica.SalidaDTOConvertidor;
import co.com.parqueadero.manejador.fabrica.dto.SalidaDTO;
import reactor.core.publisher.Mono;

public class ManejadorSalidaMoto {

    private LiquidacionMoto liquidacionMoto;
    private SalidaDTOConvertidor salidaDTOConvertidor;

    public ManejadorSalidaMoto(
            LiquidacionMoto liquidacionMoto,
            SalidaDTOConvertidor salidaDTOConvertidor
    ) {
        this.liquidacionMoto = liquidacionMoto;
        this.salidaDTOConvertidor = salidaDTOConvertidor;
    }

    public Mono<SalidaDTO> ejecutar(String placa) {
        return this.liquidacionMoto.darSalidaMoto(placa)
                .map(motoSalida -> salidaDTOConvertidor
                        .salidaDTO(motoSalida.getT1(), motoSalida.getT2())
                );
    }

}
