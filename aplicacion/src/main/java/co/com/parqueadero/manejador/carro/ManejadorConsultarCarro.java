package co.com.parqueadero.manejador.carro;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.servicios.ConsultarDatosCarro;
import reactor.core.publisher.Mono;

public class ManejadorConsultarCarro {


    private ConsultarDatosCarro consultarDatosCarro;


    public ManejadorConsultarCarro(ConsultarDatosCarro consultarDatosCarro) {
        this.consultarDatosCarro = consultarDatosCarro;
    }

    public Mono<Carro> ejecutar(String placa) {
        return this.consultarDatosCarro.ejecutar(placa);
    }


}


