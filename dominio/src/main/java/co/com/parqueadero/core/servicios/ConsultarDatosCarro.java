package co.com.parqueadero.core.servicios;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.ConsultarCarro;
import reactor.core.publisher.Mono;

public class ConsultarDatosCarro {

    private ConsultarCarro consultarCarro;

    public ConsultarDatosCarro(ConsultarCarro consultarCarro) {
        this.consultarCarro = consultarCarro;
    }

    public Mono<Carro> ejecutar(String placa) {
        return this.consultarCarro.consultarCarro(placa);
    }


}
