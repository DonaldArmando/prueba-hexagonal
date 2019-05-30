package co.com.parqueadero.validaciones;


import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarCarro;
import co.com.parqueadero.core.repositorio.IngresarMoto;
import co.com.parqueadero.core.servicios.RegistrarCarro;
import co.com.parqueadero.core.servicios.RegistrarMoto;
import co.com.parqueadero.manejador.carro.ManejadorIngresarCarro;
import co.com.parqueadero.manejador.fabrica.CarroFabrica;
import co.com.parqueadero.manejador.fabrica.MotoFabrica;
import co.com.parqueadero.manejador.moto.ManejadorIngresarMoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class ConfiguracionPrincipal {

    @Bean
    public ManejadorIngresarMoto manejadorIngresarMoto(RegistrarMoto registrarMoto,
                                                       MotoFabrica motoFabrica) {
        return new ManejadorIngresarMoto(registrarMoto, motoFabrica);
    }

    @Bean
    public RegistrarMoto registrarMoto(IngresarMoto ingresarMoto,
                                       ExistenciaVehiculo existenciaVehiculo) {
        return new RegistrarMoto(ingresarMoto, existenciaVehiculo);
    }

    @Bean
    public MotoFabrica motoFabrica() {
        return new MotoFabrica();
    }


    @Bean
    public ManejadorIngresarCarro manejadorIngresarCarro(CarroFabrica carroFabrica,
                                                         RegistrarCarro registrarCarro) {
        return new ManejadorIngresarCarro(carroFabrica, registrarCarro);
    }


    @Bean
    public RegistrarCarro registrarCarro(ExistenciaVehiculo existenciaVehiculo,
                                         IngresarCarro ingresarCarro) {
        return new RegistrarCarro(ingresarCarro, existenciaVehiculo);
    }

    @Bean
    public CarroFabrica carroFabrica() {
        return new CarroFabrica();
    }


}
