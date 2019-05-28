package co.com.parqueadero.repositorio.mongodb.enums;

public enum VehiculoType {

    MOTO("Moto"),
    CARRO("Carro");

    private final String tipo;

    VehiculoType(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
