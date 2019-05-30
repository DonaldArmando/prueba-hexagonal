package co.com.parqueadero.validaciones.general;

import co.com.parqueadero.validaciones.exepciones.ExcepcionDuplicidad;
import co.com.parqueadero.validaciones.exepciones.ExcepcionStringNoVacio;
import co.com.parqueadero.validaciones.exepciones.ExcepcionValidarNoNegativo;
import co.com.parqueadero.validaciones.exepciones.ExcepcionValorObligatorio;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.verify;


public class ValidadorArgumentoTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test()
    public void validarObligatorioCuandoEsNulo() {

        //Arrange
        String mensajeExcepcion = "Este es el mensaje de la excepcion a validar";

        //Assert
        exceptionRule.expect(ExcepcionValorObligatorio.class);
        exceptionRule.expectMessage(mensajeExcepcion);

        //Act
        ValidadorArgumento.validarObligatorio(null, mensajeExcepcion);

    }

    @Test()
    public void validadorStringNoVacioCuandoEsNulo() {

        //Arrange
        String mensajeExcepcion = "Este es el mensaje de la excepcion a validar";

        //Assert
        exceptionRule.expect(ExcepcionStringNoVacio.class);
        exceptionRule.expectMessage(mensajeExcepcion);

        //Act
        ValidadorArgumento.validadorStringNoVacio("", mensajeExcepcion);

    }



    @Test()
    public void validarNoNegativoCuandoEsNegativo() {

        //Arrange
        String mensajeExcepcion = "Este es el mensaje de la excepcion a validar";

        //Assert
        exceptionRule.expect(ExcepcionValidarNoNegativo.class);
        exceptionRule.expectMessage(mensajeExcepcion);

        //Act
        ValidadorArgumento.validarNoNegativo(-1, mensajeExcepcion);

    }


}
