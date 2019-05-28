package co.com.parqueadero.repositorio.mongodb.modelo;

import java.time.LocalDateTime;

public class CarroData extends VehiculoData {

    public CarroData(String id, String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Integer valorHora, Integer valorDia) {
        super(id, placa, fechaEntrada, fechaSalida, valorHora, valorDia);
    }


}
