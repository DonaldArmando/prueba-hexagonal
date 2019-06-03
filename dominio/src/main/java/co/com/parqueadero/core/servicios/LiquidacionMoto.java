package co.com.parqueadero.core.servicios;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.core.repositorio.ConsultarMoto;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.SalidaMoto;
import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

public class LiquidacionMoto {


    private static final String LA_MOTO_NO_SE_HA_INGRESADO = "La moto no se ha ingresado";

    private final ExistenciaVehiculo existenciaVehiculo;
    private final SalidaMoto salidaMoto;
    private final ConsultarMoto consultarMoto;

    public static String getLaMotoNoSeHaIngresado() {
        return LA_MOTO_NO_SE_HA_INGRESADO;
    }

    public LiquidacionMoto(
            ExistenciaVehiculo existenciaVehiculo,
            SalidaMoto salidaMoto,
            ConsultarMoto consultarMoto
    ) {
        this.existenciaVehiculo = existenciaVehiculo;
        this.salidaMoto = salidaMoto;

        this.consultarMoto = consultarMoto;
    }

    public Mono<Tuple2<Moto, BigInteger>> darSalidaMoto(String placa) {
        return this.validarExistenciaPrevia(placa)
                .flatMap(ignore -> this.consultarMoto.consultarMoto(placa))
                .flatMap(this::registrarSalidaMoto)
                .map(this::motoLiquidada);
    }

    private Mono<Boolean> validarExistenciaPrevia(String placa) {
        return this.existenciaVehiculo.existenciaVehiculo(placa)
                .map(existe -> {
                    if (existe) {
                        return existe;
                    }
                    throw new ExcepcionDuplicidad(LA_MOTO_NO_SE_HA_INGRESADO);

                });
    }

    private Mono<Moto> registrarSalidaMoto(Moto moto) {
        return this.salidaMoto.salidaMoto(
                moto.getPlaca(),
                moto.getFechaSalida());
    }

    private Tuple2<Moto, BigInteger> motoLiquidada(Moto moto) {
        return Tuples.of(
                moto,
                calcularValorMoto(
                        moto.getFechaEntrada(),
                        moto.getFechaSalida(),
                        moto.getCilindraje(),
                        moto.getValorHora(),
                        moto.getValorDia())
        );
    }

    private BigInteger calcularValorMoto(
            LocalDateTime horaIngreso,
            LocalDateTime horaSalida,
            Integer cilindraje,
            Integer valorHora,
            Integer valorDia
    ) {
        BigInteger valor = BigInteger.ZERO;
        Duration duration = Duration.between(horaIngreso, horaSalida);
        Double horas = calcularHorasPorDias(duration.getSeconds());
        Double decimales = calcularDecimalesHoraPorDias(horas);
        valor = valor.add(BigInteger.valueOf(horas.intValue() * valorDia));

        if (decimales * 24 >= 9) {
            valor = valor.add(BigInteger.valueOf(valorDia));
        } else {
            valor = valor.add(BigInteger.valueOf(Double.valueOf(decimales * 24).intValue() * valorHora));
        }

        if (cilindraje > 500) {
            valor = valor.add(BigInteger.valueOf(2000));
        }

        return valor;
    }


    private Double calcularHorasPorDias(Long segundos) {
        return (Math.ceil(new Double(segundos) / 3600)) / 24;
    }

    private Double calcularDecimalesHoraPorDias(Double horasPorDia) {
        return horasPorDia - horasPorDia.intValue();
    }

}
