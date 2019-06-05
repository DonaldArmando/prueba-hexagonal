package co.com.parqueadero.repositorio.mongodb.convertidores;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.CarroData;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;

public final class CarroConvertidor {
    private CarroConvertidor() {
    }

    public static Registro<CarroData> carroRegistro(Carro carro) {
        CarroData registoMoto = new CarroData(
                null,
                carro.getPlaca(),
                null,
                null,
                null,
                null);
        return new Registro<>(
                null,
                registoMoto,
                VehiculoType.CARRO,
                carro.getFechaEntrada(),
                carro.getFechaSalida(),
                carro.getValorHora(),
                carro.getValorDia()
        );
    }

    public static Carro registroCarro(Registro<CarroData> registro) {
        return new Carro(
                registro.getRegisto().getPlaca(),
                registro.getFechaEntrada(),
                registro.getFechaSalida(),
                registro.getValorHora(),
                registro.getValorDia()
        );
    }


}
