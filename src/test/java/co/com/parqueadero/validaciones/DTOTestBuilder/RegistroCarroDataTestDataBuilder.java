package co.com.parqueadero.validaciones.DTOTestBuilder;

import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.CarroData;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;

import java.time.LocalDateTime;

public class RegistroCarroDataTestDataBuilder {
    private String id;
    private CarroData registro;
    private VehiculoType tipo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;


    public RegistroCarroDataTestDataBuilder() {
        this.id = null;
        this.registro = new CarroData(null, "jjj-333", null, null, null, null);
        this.tipo = VehiculoType.CARRO;
        this.fechaEntrada = LocalDateTime.of(2019, 06, 03, 10, 10);
        this.fechaSalida = null;
        this.valorHora = 1000;
        this.valorDia = 8000;
    }

    public Registro<CarroData> build() {
        return new Registro<>(
                this.id,
                this.registro,
                this.tipo,
                this.fechaEntrada,
                this.fechaSalida,
                this.valorHora,
                this.valorDia
        );
    }



}
