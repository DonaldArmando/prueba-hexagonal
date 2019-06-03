package co.com.parqueadero.validaciones.DTOTestBuilder;

import co.com.parqueadero.manejador.fabrica.dto.MotoDTO;

import java.time.LocalDateTime;


public class MotoDTOTestBuilder {


    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;
    private Integer cilindraje;


    public MotoDTOTestBuilder() {
        this.placa = "BBC-123";
        this.fechaEntrada = null;
        this.fechaSalida = null;
        this.valorHora = null;
        this.valorDia = null;
        this.cilindraje = 125;
    }



    public MotoDTOTestBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public MotoDTOTestBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public MotoDTOTestBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public MotoDTOTestBuilder conCilindraje(Integer cilindraje) {
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
