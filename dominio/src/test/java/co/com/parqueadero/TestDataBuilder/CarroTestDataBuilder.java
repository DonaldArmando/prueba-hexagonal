package co.com.parqueadero.TestDataBuilder;

import co.com.parqueadero.core.modelos.Carro;
import co.com.parqueadero.core.modelos.Moto;

import java.time.LocalDateTime;

public class CarroTestDataBuilder {

    private String placa;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;
    private Integer cilindraje;


    public CarroTestDataBuilder() {
        this.placa = "BBC 123";
        this.fechaEntrada = LocalDateTime.of(1992, 10, 20, 10, 20);
        this.fechaSalida = null;
        this.valorHora = 1000;
        this.valorDia = 8000;
        this.cilindraje = 125;
    }


    public CarroTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public CarroTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
        return this;
    }

    public CarroTestDataBuilder conFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
        return this;
    }

    public CarroTestDataBuilder conCilindraje(Integer cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }

    public Carro build() {
        return new Carro(
                this.placa,
                this.fechaEntrada,
                this.fechaSalida,
                this.valorHora,
                this.valorDia
        );
    }


}
