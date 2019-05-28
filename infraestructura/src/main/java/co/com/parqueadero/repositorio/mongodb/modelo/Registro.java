package co.com.parqueadero.repositorio.mongodb.modelo;

import co.com.parqueadero.repositorio.mongodb.enums.VehiculoType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "registro")
public class Registro<T> {

    @Id
    private String id;
    private T registo;
    private VehiculoType tipo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer valorHora;
    private Integer valorDia;

    public Registro() {
    }

    public Registro(String id, T registo, VehiculoType tipo, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, Integer valorHora, Integer valorDia) {
        this.id = id;
        this.registo = registo;
        this.tipo = tipo;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorHora = valorHora;
        this.valorDia = valorDia;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id='" + id + '\'' +
                ", registo=" + registo +
                ", tipo=" + tipo +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", valorHora=" + valorHora +
                ", valorDia=" + valorDia +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registro<?> registro = (Registro<?>) o;
        return Objects.equals(id, registro.id) &&
                Objects.equals(registo, registro.registo) &&
                tipo == registro.tipo &&
                Objects.equals(fechaEntrada, registro.fechaEntrada) &&
                Objects.equals(fechaSalida, registro.fechaSalida) &&
                Objects.equals(valorHora, registro.valorHora) &&
                Objects.equals(valorDia, registro.valorDia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registo, tipo, fechaEntrada, fechaSalida, valorHora, valorDia);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getRegisto() {
        return registo;
    }

    public void setRegisto(T registo) {
        this.registo = registo;
    }

    public VehiculoType getTipo() {
        return tipo;
    }

    public void setTipo(VehiculoType tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDateTime fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getValorHora() {
        return valorHora;
    }

    public void setValorHora(Integer valorHora) {
        this.valorHora = valorHora;
    }

    public Integer getValorDia() {
        return valorDia;
    }

    public void setValorDia(Integer valorDia) {
        this.valorDia = valorDia;
    }
}
