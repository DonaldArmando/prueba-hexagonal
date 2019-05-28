package co.com.parqueadero.TestDataBuilder;

import co.com.parqueadero.core.modelos.Moto;

import java.time.LocalDateTime;

public class MotoTestDataBuilder {

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;
    private Integer cilindraje;


    public MotoTestDataBuilder() {
        this.placa = "ABC 123";
        this.fechaEntrada = LocalDateTime.now();
        this.fechaSalida = null;
        this.valorHora = 1000;
        this.valorDia = 9000;
        this.cilindraje = 125;
    }


    public MotoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public MotoTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public MotoTestDataBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public MotoTestDataBuilder conCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }

    public Moto build() {
        return new Moto(
                this.placa,
                this.fechaEntrada,
                this.fechaSalida,
                this.valorHora,
                this.valorDia,
                this.cilindraje
        );
    }

}
