package co.com.parqueadero.testDataBuilder;

import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;

import java.time.LocalDateTime;

public class MotoDTOTestDataBuilder {

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;
    private Integer cilindraje;

    public MotoDTOTestDataBuilder() {
        this.placa = "ABC 123 abc";
        this.fechaEntrada = null;
        this.fechaSalida = null;
        this.valorHora = null;
        this.valorDia = null;
        this.cilindraje = 125;
    }


    public MotoDTOTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public MotoDTOTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public MotoDTOTestDataBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public MotoDTOTestDataBuilder conCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }

    public MotoDTO build() {
        return new MotoDTO(
                this.placa,
                this.fechaEntrada,
                this.fechaSalida,
                this.valorHora,
                this.valorDia,
                this.cilindraje
        );
    }


}
