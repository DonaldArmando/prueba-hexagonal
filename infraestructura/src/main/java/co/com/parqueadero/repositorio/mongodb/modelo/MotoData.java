package co.com.parqueadero.repositorio.mongodb.modelo;

import java.time.LocalDateTime;

public class MotoData extends VehiculoData {

    private Integer cilindraje;

    public MotoData(String id, String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Integer valorHora, Integer valorDia, Integer cilindraje) {
        super(id, placa, fechaEntrada, fechaSalida, valorHora, valorDia);
        this.cilindraje = cilindraje;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }




}
