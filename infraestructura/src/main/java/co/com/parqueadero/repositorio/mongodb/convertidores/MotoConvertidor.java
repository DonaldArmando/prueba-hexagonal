package co.com.parqueadero.repositorio.mongodb.convertidores;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.MotoData;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;

public final class MotoConvertidor {

    public static Registro<MotoData> motoRegistro(Moto moto) {
        MotoData registoMoto = new MotoData(
                null,
                moto.getPlaca(),
                null,
                null,
                null,
                null,
                moto.getCilindraje());
        return new Registro<>(
                null,
                registoMoto,
                VehiculoType.MOTO,
                moto.getFechaEntrada(),
                moto.getFechaSalida(),
                moto.getValorDia(),
                moto.getValorHora()
        );
    }


    public static Moto registroMoto(Registro<MotoData> registro) {
        return new Moto(
                registro.getRegisto().getPlaca(),
                registro.getFechaEntrada(),
                registro.getFechaSalida(),
                registro.getValorHora(),
                registro.getValorDia(),
                registro.getRegisto().getCilindraje()
        );
    }


}
