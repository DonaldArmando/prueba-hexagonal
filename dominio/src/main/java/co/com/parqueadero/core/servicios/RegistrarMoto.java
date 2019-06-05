package co.com.parqueadero.core.servicios;

import co.com.parqueadero.core.configuracion.ConfiguracionGeneral;
import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.CantidadMoto;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.IngresarMoto;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDiaSemana;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import co.com.parqueadero.validaciones.exepciones.ExcepcionMaximoCupos;
import co.com.parqueadero.validaciones.utilidades.UtilidadFecha;
import reactor.core.publisher.Mono;

import java.time.DayOfWeek;

public class RegistrarMoto {

    private static final String LA_MOTO_AUN_NO_SE_LIQUIDA = "La moto aun no se liquida";
    private static final String CUPO_MAXIMO_MOTOS_OCUPADO = "Cupo maximo de motos esta ocupado";
    private static final String PARA_LAS_PLACAS_QUE_EMPIECEN_POR_A_SOLO_PUEDEN_INGRESAR_LOS_LUNES_Y_DOMINGOS = "Para las placas que empicen por la letra a solo pueden ingresar los lunes y domingos";


    private final IngresarMoto ingresarMoto;
    private final ExistenciaVehiculo existenciaVehiculo;
    private final CantidadMoto cantidadMoto;
    private final UtilidadFecha utilidadFecha;

    public static String getLaMotoAunNoSeLiquida() {
        return LA_MOTO_AUN_NO_SE_LIQUIDA;
    }

    public static String getCupoMaximoMotosOcupado() {
        return CUPO_MAXIMO_MOTOS_OCUPADO;
    }


    public static String getParaLasPlacasQueEmpiecenPorASoloPuedenIngresarLosLunesYDomingos() {
        return PARA_LAS_PLACAS_QUE_EMPIECEN_POR_A_SOLO_PUEDEN_INGRESAR_LOS_LUNES_Y_DOMINGOS;
    }

    public RegistrarMoto(
            IngresarMoto ingresarMoto,
            ExistenciaVehiculo existenciaVehiculo,
            CantidadMoto cantidadMoto,
            UtilidadFecha utilidadFecha
    ) {
        this.ingresarMoto = ingresarMoto;
        this.existenciaVehiculo = existenciaVehiculo;
        this.cantidadMoto = cantidadMoto;
        this.utilidadFecha = utilidadFecha;
    }

    public Mono<Moto> ejecutar(Moto moto) {
        return this.validarExistenciaPrevia(moto.getPlaca())
                .flatMap(ignore -> this.validarCantidadPermitida())
                .flatMap(ignore -> this.validarPrimeraLetraPlaca(moto.getPlaca()))
                .map(valor -> moto)
                .flatMap(ingresarMoto::ingresarMoto);
    }

    private Mono<Boolean> validarExistenciaPrevia(String placa) {
        return this.existenciaVehiculo.existenciaVehiculo(placa)
                .map(existe -> {
                    if (existe) {
                        throw new ExcepcionDuplicidad(LA_MOTO_AUN_NO_SE_LIQUIDA);
                    }
                    return existe;
                });
    }

    private Mono<Boolean> validarCantidadPermitida() {
        return this.cantidadMoto.cantidadMoto()
                .map(cantidad -> {
                    if (cantidad > ConfiguracionGeneral.MAXIMO_CUPO_MOTOS) {
                        throw new ExcepcionMaximoCupos(CUPO_MAXIMO_MOTOS_OCUPADO);
                    }
                    return Boolean.TRUE;
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


}
