package co.com.parqueadero.validaciones.DTOTestBuilder;

import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;

import java.time.LocalDateTime;

public class CarroDTOTestBuilder {

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;


    public CarroDTOTestBuilder() {
        this.placa = "BBC-223";
        this.fechaEntrada = null;
        this.fechaSalida = null;
        this.valorHora = null;
        this.valorDia = null;
    }


    public CarroDTOTestBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public CarroDTOTestBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public CarroDTOTestBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }


    public CarroDTO build() {
        return new CarroDTO(
                this.placa,
                this.fechaEntrada,
                this.fechaSalida,
                this.valorHora,
                this.valorDia
        );
    }


}
