package br.com.rentacar.reserveserviceapplication.exceptions;

import org.apache.http.client.HttpResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpResponseException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            HttpResponseException ex) {
        ExceptionTreatment exceptionTreatment = new ExceptionTreatment(ex.getMessage(), ex.getStatusCode());
        return expression(exceptionTreatment);
    }

    private ResponseEntity<Object> expression(ExceptionTreatment exceptionTreatment) {
        return new ResponseEntity<Object>(exceptionTreatment, HttpStatus.valueOf(exceptionTreatment.getStatusCode()));
    }
}
