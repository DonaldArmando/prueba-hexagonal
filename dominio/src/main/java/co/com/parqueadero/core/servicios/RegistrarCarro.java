package co.com.parqueadero.core.servicios;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.CantidadCarro;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarCarro;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDiaSemana;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import co.com.parqueadero.validaciones.exepciones.ExcepcionMaximoCupos;
import co.com.parqueadero.validaciones.utilidades.UtilidadFecha;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;


public class RegistrarCarro {

    private static final String EL_CARRO_AUN_NO_SE_LIQUIDA = "El carro aun no se liquida";
    private static final String CUPO_MAXIMO_CARROS_OCUPADO = "Cupo maximo de carros esta ocupado";
    private static final String PARA_LAS_PLACAS_QUE_EMPIECEN_POR_A_SOLO_PUEDEN_INGRESAR_LOS_LUNES_Y_DOMINGOS = "Para las placas que empicen por la letra a solo pueden ingresar los lunes y domingos";

    private final IngresarCarro ingresarCarro;
    private final ExistenciaVehiculo existenciaVehiculo;
    private final CantidadCarro cantidadCarro;
    private final UtilidadFecha utilidadFecha;

    public static String getElCarroAunNoSeLiquida() {
        return EL_CARRO_AUN_NO_SE_LIQUIDA;
    }

    public static String getCupoMaximoCarrosOcupado() {
        return CUPO_MAXIMO_CARROS_OCUPADO;
    }

    public static String getParaLasPlacasQueEmpiecenPorASoloPuedenIngresarLosLunesYDomingos() {
        return PARA_LAS_PLACAS_QUE_EMPIECEN_POR_A_SOLO_PUEDEN_INGRESAR_LOS_LUNES_Y_DOMINGOS;
    }

    public RegistrarCarro(
            IngresarCarro ingresarCarro,
            ExistenciaVehiculo existenciaVehiculo,
            CantidadCarro cantidadCarro,
            UtilidadFecha utilidadFecha
    ) {
        this.ingresarCarro = ingresarCarro;
        this.existenciaVehiculo = existenciaVehiculo;
        this.cantidadCarro = cantidadCarro;
        this.utilidadFecha = utilidadFecha;
    }

    public Mono<Carro> ejecutar(Carro carro) {
        return this.validarExistenciaPrevia(carro.getPlaca())
                .flatMap(ignore -> this.validarCantidadPermitida())
                .flatMap(ignore -> this.validarPrimeraLetraPlaca(carro.getPlaca()))
                .map(valor -> carro)
                .flatMap(ingresarCarro::ingresarCarro);
    }

    private Mono<Boolean> validarExistenciaPrevia(String placa) {
        return this.existenciaVehiculo.existenciaVehiculo(placa)
                .map(existe -> {
                    if (existe) {
                        throw new ExcepcionDuplicidad(EL_CARRO_AUN_NO_SE_LIQUIDA);
                    }
                    return existe;
                });
    }

    private Mono<Boolean> validarPrimeraLetraPlaca(String placa) {
        Character primeraLetra = placa.charAt(0);
        DayOfWeek diaDeLaSemana = utilidadFecha.fechaActual().getDayOfWeek();
        Boolean validarLetra = primeraLetra.equals('a') || primeraLetra.equals('A');
        Boolean validarDiaDeLaSemana = diaDeLaSemana.equals(DayOfWeek.MONDAY) || diaDeLaSemana.equals(DayOfWeek.SUNDAY);
        if (!validarDiaDeLaSemana && validarLetra) {
            throw new ExcepcionDiaSemana(PARA_LAS_PLACAS_QUE_EMPIECEN_POR_A_SOLO_PUEDEN_INGRESAR_LOS_LUNES_Y_DOMINGOS);
        }
        return Mono.just(Boolean.TRUE);

    }

    private Mono<Boolean> validarCantidadPermitida() {
        return this.cantidadCarro.cantidadCarro()
                .map(cantidad -> {
                    if (cantidad > 20) {
                        throw new ExcepcionMaximoCupos(CUPO_MAXIMO_CARROS_OCUPADO);
                    }
                    return Boolean.TRUE;
                });
    }

}
