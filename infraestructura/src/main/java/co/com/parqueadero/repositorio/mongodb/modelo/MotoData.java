package co.com.parqueadero.repositorio.mongodb.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

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


    @Override
    public String toString() {
        return "MotoData{" +
                "cilindraje=" + cilindraje +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MotoData)) return false;
        MotoData motoData = (MotoData) o;
        return Objects.equals(getCilindraje(), motoData.getCilindraje());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCilindraje());
    }
}
