package co.com.parqueadero.manejador.fabrica.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class MotoDTO {

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;
    private Integer cilindraje;


    public MotoDTO() {
    }


    public MotoDTO(String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Integer valorHora, Integer valorDia, Integer cilindraje) {
        this.placa = placa;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorHora = valorHora;
        this.valorDia = valorDia;
        this.cilindraje = cilindraje;
    }

    @Override
    public String toString() {
        return "MotoDTO{" +
                "placa='" + placa + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", valorHora=" + valorHora +
                ", valorDia=" + valorDia +
                ", cilindraje=" + cilindraje +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotoDTO motoDTO = (MotoDTO) o;
        return Objects.equals(placa, motoDTO.placa) &&
                Objects.equals(fechaEntrada, motoDTO.fechaEntrada) &&
                Objects.equals(fechaSalida, motoDTO.fechaSalida) &&
                Objects.equals(valorHora, motoDTO.valorHora) &&
                Objects.equals(valorDia, motoDTO.valorDia) &&
                Objects.equals(cilindraje, motoDTO.cilindraje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa, fechaEntrada, fechaSalida, valorHora, valorDia, cilindraje);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getValorHora() {
        return valorHora;
    }

    public void setValorHora(Integer valorHora) {
        this.valorHora = valorHora;
    }

    public Integer getValorDia() {
        return valorDia;
    }

    public void setValorDia(Integer valorDia) {
        this.valorDia = valorDia;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
    }
}
