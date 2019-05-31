package co.com.parqueadero.core.servicios;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.repositorio.ConsultarCarro;
import co.com.parqueadero.core.repositorio.ExistenciaVehiculo;
import co.com.parqueadero.core.repositorio.SalidaCarro;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;

public class LiquidacionCarro {

    private final ExistenciaVehiculo existenciaVehiculo;
    private final SalidaCarro salidaMoto;
    private final ConsultarCarro consultarMoto;

    public LiquidacionCarro(
            ExistenciaVehiculo existenciaVehiculo,
            SalidaCarro salidaCarro,
            ConsultarCarro consultarCarro
    ) {
        this.existenciaVehiculo = existenciaVehiculo;
        this.salidaMoto = salidaCarro;
        this.consultarMoto = consultarCarro;
    }

    public Mono<Tuple2<Carro, BigInteger>> darSalidaMoto(String placa) {
        return this.consultarMoto.consultarCarro(placa)
                .flatMap(this::registrarSalidaCarro)
                .map(this::carroLiquidada);
    }

    private Mono<Carro> registrarSalidaCarro(Carro carro) {
        return this.salidaMoto.salidaCarro(
                carro.getPlaca(),
                carro.getFechaSalida());
    }

    private Tuple2<Carro, BigInteger> carroLiquidada(Carro carro) {
        return Tuples.of(
                carro,
                calcularValorCarro(
                        carro.getFechaEntrada(),
                        carro.getFechaSalida(),
                        carro.getValorHora(),
                        carro.getValorDia())
        );
    }

    private BigInteger calcularValorCarro(
            LocalDateTime horaIngreso,
            LocalDateTime horaSalida,
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

        return valor;
    }


    private Double calcularHorasPorDias(Long segundos) {
        return (Math.ceil(new Double(segundos) / 3600)) / 24;
    }

    private Double calcularDecimalesHoraPorDias(Double horasPorDia) {
        return horasPorDia - horasPorDia.intValue();
    }


}
