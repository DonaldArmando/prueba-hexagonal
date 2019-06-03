package co.com.parqueadero.repositorio.mongodb.modelo;

import java.time.LocalDateTime;
import java.util.Objects;


public class VehiculoData {

    private String id;
    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;


    public VehiculoData(String id, String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Integer valorHora, Integer valorDia) {
        this.id = id;
        this.placa = placa;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorHora = valorHora;
        this.valorDia = valorDia;
    }


    @Override
    public String toString() {
        return "VehiculoData{" +
                "id='" + id + '\'' +
                ", placa='" + placa + '\'' +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", valorHora=" + valorHora +
                ", valorDia=" + valorDia +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
