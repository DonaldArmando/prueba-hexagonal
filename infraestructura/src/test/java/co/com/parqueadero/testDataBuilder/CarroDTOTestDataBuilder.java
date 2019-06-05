package co.com.parqueadero.testDataBuilder;

import co.com.parqueadero.manejador.fabrica.dto.CarroDTO;

import java.time.LocalDateTime;

public class CarroDTOTestDataBuilder {

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;

    public CarroDTOTestDataBuilder() {
        this.placa = "ABC 123 abc";
        this.fechaEntrada = null;
        this.fechaSalida = null;
        this.valorHora = null;
        this.valorDia = null;
    }


    public CarroDTOTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public CarroDTOTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public CarroDTOTestDataBuilder conFechaSalida(LocalDateTime fechaSalida) {
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
