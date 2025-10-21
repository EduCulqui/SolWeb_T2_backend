package com.health.exception;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.LocalDateTime;

@RestControllerAdvice // Interceptar excepcion
public class ResponseExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorRecord> handleDefaultExceptions(Exception ex, WebRequest request){
        CustomErrorRecord err = new CustomErrorRecord(
                LocalDateTime.now(), ex.getMessage(), request.getDescription(false)
        );
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorRecord> handleModelNotFoundException(Exception ex, WebRequest request){
        CustomErrorRecord err = new CustomErrorRecord(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<CustomErrorRecord> handleArithmeticException(ArithmeticException ex, WebRequest request){
        CustomErrorRecord err = new CustomErrorRecord(
                LocalDateTime.now(), ex.getMessage(), request.getDescription(false)
        );
        return new ResponseEntity<>(err, HttpStatus.NOT_ACCEPTABLE);
    }

    // Desde Spring Boot 3+
    /*@ExceptionHandler(ModelNotFoundException.class)
    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest request){
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        pd.setTitle("Model Not Found Exception");
        pd.setType(URI.create(request.getDescription(false)));
        pd.setProperty("extra1", "extra-value");
        pd.setProperty("extra2", 45);
        return pd;
    }*/

    /*@ExceptionHandler(ModelNotFoundException.class)
    public ErrorResponse hanldeModelNotFoundException(ModelNotFoundException ex, WebRequest request){
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage())
                .title("Model Not Found Exception")
                .type(URI.create(request.getDescription(false)))
                .property("extra1", "valor-extra")
                .property("extra2", 100)
                .build();
    }*/
}
