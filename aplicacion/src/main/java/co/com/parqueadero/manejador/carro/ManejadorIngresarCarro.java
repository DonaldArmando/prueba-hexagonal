package co.com.parqueadero.manejador.carro;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.servicios.RegistrarCarro;
import co.com.parqueadero.manejador.fabrica.CarroFabrica;
import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;
import reactor.core.publisher.Mono;

public class ManejadorIngresarCarro {

    private CarroFabrica carroFabrica;
    private RegistrarCarro registrarCarro;


    public ManejadorIngresarCarro(CarroFabrica carroFabrica, RegistrarCarro registrarCarro) {
        this.carroFabrica = carroFabrica;
        this.registrarCarro = registrarCarro;
    }

    public Mono<Carro> ejecutar(CarroDTO carroDTO) {
        Carro carro = this.carroFabrica.motoDTOMoto(carroDTO);
        return this.registrarCarro.ejecutar(carro);
    }

}
