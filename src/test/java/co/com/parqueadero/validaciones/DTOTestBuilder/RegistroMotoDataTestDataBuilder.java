package co.com.parqueadero.validaciones.DTOTestBuilder;

import co.com.parqueadero.core.modelos.Moto;
import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import co.com.parqueadero.repositorio.mongodb.modelo.MotoData;
import co.com.parqueadero.repositorio.mongodb.modelo.Registro;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;

public class RegistroMotoDataTestDataBuilder {

    private String id;
    private MotoData registro;
    private VehiculoType tipo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;


    public RegistroMotoDataTestDataBuilder() {
        this.id = null;
        this.registro = new MotoData(null, "BBC-123", null, null, null, null, 125);
        this.tipo = VehiculoType.MOTO;
        this.fechaEntrada = LocalDateTime.of(2019, 06, 03, 10, 10);
        this.fechaSalida = null;
        this.valorHora = 4000;
        this.valorDia = 500;
    }

    public Registro<MotoData> build() {
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
