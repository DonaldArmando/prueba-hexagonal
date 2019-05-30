package co.com.parqueadero.manejador.fabrica.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class CarroDTO {

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;

    public CarroDTO(String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Integer valorHora, Integer valorDia) {
        this.placa = placa;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorHora = valorHora;
        this.valorDia = valorDia;
    }

    public CarroDTO() {
    }

    @Override
    public String toString() {
        return "CarroDTO{" +
                "placa='" + placa + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", valorHora=" + valorHora +
                ", valorDia=" + valorDia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarroDTO carroDTO = (CarroDTO) o;
        return Objects.equals(placa, carroDTO.placa) &&
                Objects.equals(fechaEntrada, carroDTO.fechaEntrada) &&
                Objects.equals(fechaSalida, carroDTO.fechaSalida) &&
                Objects.equals(valorHora, carroDTO.valorHora) &&
                Objects.equals(valorDia, carroDTO.valorDia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placa, fechaEntrada, fechaSalida, valorHora, valorDia);
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
}
