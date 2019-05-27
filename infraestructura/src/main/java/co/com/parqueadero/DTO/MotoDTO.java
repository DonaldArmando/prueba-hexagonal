package co.com.parqueadero.DTO;

import java.time.LocalDateTime;

public class MotoDTO {

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;
    private Integer cilindraje;


    public MotoDTO(String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Integer valorHora, Integer valorDia, Integer cilindraje) {
        this.placa = placa;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorHora = valorHora;
        this.valorDia = valorDia;
        this.cilindraje = cilindraje;
    }


    public MotoDTO() {
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public Integer getValorHora() {
        return valorHora;
    }

    public Integer getValorDia() {
        return valorDia;
    }

    public Integer getCilindraje() {
        return cilindraje;
    }
}
