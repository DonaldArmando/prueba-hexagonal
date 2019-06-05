package co.com.parqueadero.validaciones;


import co.com.parqueadero.core.repositorio.*;
import co.com.parqueadero.core.servicios.*;
import co.com.parqueadero.manejador.carro.ManejadorConsultarCarro;
import co.com.parqueadero.manejador.carro.ManejadorIngresarCarro;
import co.com.parqueadero.manejador.carro.ManejadorSalidaCarro;
import co.com.parqueadero.manejador.fabrica.CarroFabrica;
import co.com.parqueadero.manejador.fabrica.MotoFabrica;
import co.com.parqueadero.manejador.fabrica.SalidaDTOConvertidor;
import co.com.parqueadero.manejador.moto.ManejadorConsultarMoto;
import co.com.parqueadero.manejador.moto.ManejadorIngresarMoto;
import co.com.parqueadero.manejador.moto.ManejadorSalidaMoto;
import co.com.parqueadero.validaciones.utilidades.UtilidadFecha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionPrincipal {

    @Bean
    public ManejadorIngresarMoto manejadorIngresarMoto(RegistrarMoto registrarMoto,
                                                       MotoFabrica motoFabrica) {
        return new ManejadorIngresarMoto(registrarMoto, motoFabrica);
    }

    @Bean
    public RegistrarMoto registrarMoto(IngresarMoto ingresarMoto,
                                       ExistenciaVehiculo existenciaVehiculo,
                                       CantidadMoto cantidadMoto,
                                       UtilidadFecha utilidadFecha) {
        return new RegistrarMoto(
                ingresarMoto,
                existenciaVehiculo,
                cantidadMoto,
                utilidadFecha
        );
    }

    @Bean
    public MotoFabrica motoFabrica(UtilidadFecha utilidadFecha) {
        return new MotoFabrica(utilidadFecha);
    }


    @Bean
    public ManejadorIngresarCarro manejadorIngresarCarro(CarroFabrica carroFabrica,
                                                         RegistrarCarro registrarCarro) {
        return new ManejadorIngresarCarro(carroFabrica, registrarCarro);
    }


    @Bean
    public RegistrarCarro registrarCarro(ExistenciaVehiculo existenciaVehiculo,
                                         IngresarCarro ingresarCarro,
                                         CantidadCarro cantidadCarro,
                                         UtilidadFecha utilidadFecha) {
        return new RegistrarCarro(
                ingresarCarro,
                existenciaVehiculo,
                cantidadCarro,
                utilidadFecha
        );
    }

    @Bean
    public CarroFabrica carroFabrica(UtilidadFecha utilidadFecha) {
        return new CarroFabrica(utilidadFecha);
    }


    @Bean
    public ManejadorConsultarMoto manejadorConsultarMoto(ConsultarDatosMoto consultarDatosMoto) {
        return new ManejadorConsultarMoto(consultarDatosMoto);
    }

    @Bean
    public ConsultarDatosMoto consultarDatosMoto(ConsultarMoto consultarMoto) {
        return new ConsultarDatosMoto(consultarMoto);
    }

    @Bean
    public ManejadorConsultarCarro manejadorConsultarCarro(ConsultarDatosCarro consultarDatosCarro) {
        return new ManejadorConsultarCarro(consultarDatosCarro);
    }

    @Bean
    public ConsultarDatosCarro consultarDatosCarro(ConsultarCarro consultarCarro) {
        return new ConsultarDatosCarro(consultarCarro);
    }


    @Bean
    public ManejadorSalidaMoto manejadorSalidaMoto(
            LiquidacionMoto liquidacionMoto,
            SalidaDTOConvertidor salidaDTOConvertidor
    ) {
        return new ManejadorSalidaMoto(liquidacionMoto, salidaDTOConvertidor);
    }

    @Bean
    public LiquidacionMoto liquidacionMoto(
            ExistenciaVehiculo existenciaVehiculo,
            SalidaMoto salidaMoto,
            ConsultarMoto consultarMoto
    ) {
        return new LiquidacionMoto(
                existenciaVehiculo,
                salidaMoto,
                consultarMoto);
    }

    @Bean
    public ManejadorSalidaCarro manejadorSalidaCarro(
            LiquidacionCarro liquidacionCarro,
            SalidaDTOConvertidor salidaDTOConvertidor
    ) {
        return new ManejadorSalidaCarro(
                liquidacionCarro,
                salidaDTOConvertidor);
    }

    @Bean
    public LiquidacionCarro liquidacionCarro(ExistenciaVehiculo existenciaVehiculo,
                                             SalidaCarro salidaMoto,
                                             ConsultarCarro consultarMoto
    ) {
        return new LiquidacionCarro(existenciaVehiculo, salidaMoto, consultarMoto);
    }


    @Bean
    public SalidaDTOConvertidor salidaDTOConvertidor() {
        return new SalidaDTOConvertidor();
    }


    @Bean
    public UtilidadFecha utilidadFecha() {
        return new UtilidadFecha();
    }
}


