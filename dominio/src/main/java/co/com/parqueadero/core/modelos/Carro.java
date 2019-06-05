package co.com.parqueadero.core.modelos;

import java.time.LocalDateTime;

public class Carro extends Vehiculo {

    public Carro(String placa,
                 LocalDateTime fechaEntrada,
                 LocalDateTime fechaSalida,
                 Integer valorHora,
                 Integer valorDia) {
        super(placa, fechaEntrada, fechaSalida, valorHora, valorDia);
    }


}
