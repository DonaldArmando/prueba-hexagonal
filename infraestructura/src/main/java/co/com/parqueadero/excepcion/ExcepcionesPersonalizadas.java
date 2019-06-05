package co.com.parqueadero.excepcion;


import co.com.parqueadero.excepcion.modelo.InformacionError;
import co.com.parqueadero.validaciones.exepciones.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class ExcepcionesPersonalizadas {


    @ExceptionHandler({
            ExcepcionValorObligatorio.class,
            ExcepcionStringNoVacio.class,
            ExcepcionDiaSemana.class,
            ExcepcionDuplicidad.class,
            ExcepcionFechaNoAnterior.class,
            ExcepcionMaximoCupos.class,
            ExcepcionValidarNoNegativo.class
    })
    public Mono<ResponseEntity<InformacionError>> excepcionObligatorio(RuntimeException runtimeException) {
        return this.excepcionGenerica(
                runtimeException.getClass().toString(),
                runtimeException.getMessage()
        );
    }


    private Mono<ResponseEntity<InformacionError>> excepcionGenerica(String claseError, String mensajeError) {
        return Mono.just(ResponseEntity
                .badRequest()
                .body(new InformacionError(
                        claseError,
                        HttpStatus.BAD_REQUEST,
                        HttpStatus.BAD_REQUEST.value(),
                        mensajeError)));
    }


}
