package co.com.parqueadero.manejador.moto;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.servicios.ConsultarDatosMoto;
import reactor.core.publisher.Mono;

public class ManejadorConsultarMoto {

    private ConsultarDatosMoto consultarDatosMoto;


    public ManejadorConsultarMoto(ConsultarDatosMoto consultarDatosMoto) {
        this.consultarDatosMoto = consultarDatosMoto;
    }

    public Mono<Moto>  ejecutar(String placa) {
        return this.consultarDatosMoto.ejecutar(placa);
    }


}