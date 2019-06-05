package co.com.parqueadero.excepcion.modelo;

import org.springframework.http.HttpStatus;

public class InformacionError {

    private String claseError;
    private HttpStatus tipoError;
    private Integer codigoError;
    private String mensajeError;


    public InformacionError(String claseError, HttpStatus tipoError, Integer codigoError, String mensajeError) {
        this.claseError = claseError;
        this.tipoError = tipoError;
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
    }

    public InformacionError() {
    }


    public String getClaseError() {
        return claseError;
    }

    public void setClaseError(String claseError) {
        this.claseError = claseError;
    }

    public HttpStatus getTipoError() {
        return tipoError;
    }

    public void setTipoError(HttpStatus tipoError) {
        this.tipoError = tipoError;
    }

    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}
