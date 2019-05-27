package co.com.parqueadero.validaciones;


import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarMoto;
import co.com.parqueadero.core.servicios.RegistrarMoto;
import co.com.parqueadero.manejador.moto.ManejadorIngresarMoto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfiguracionPrincipal {


    @Bean
    public ManejadorIngresarMoto manejadorIngresarMoto() {
        return new ManejadorIngresarMoto();
    }


}
