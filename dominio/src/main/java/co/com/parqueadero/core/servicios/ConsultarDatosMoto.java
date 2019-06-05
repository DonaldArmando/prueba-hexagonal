package co.com.parqueadero.core.servicios;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.ConsultarMoto;
import reactor.core.publisher.Mono;

public class ConsultarDatosMoto {

    private ConsultarMoto consultarMoto;

    public ConsultarDatosMoto(ConsultarMoto consultarMoto) {
        this.consultarMoto = consultarMoto;
    }

    public Mono<Moto> ejecutar(String placa) {
        return this.consultarMoto.consultarMoto(placa);
    }


}
