package co.com.parqueadero.validaciones;


import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarMoto;
import co.com.parqueadero.core.servicios.RegistrarMoto;
import co.com.parqueadero.manejador.fabrica.MotoFabrica;
import co.com.parqueadero.manejador.moto.ManejadorIngresarMoto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfiguracionPrincipal {


    @Bean
    public ManejadorIngresarMoto manejadorIngresarMoto(RegistrarMoto registrarMoto, MotoFabrica motoFabrica) {
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


}
