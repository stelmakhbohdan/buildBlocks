package com.simplerest.buildblocks.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"From MethodArgumentValidException in GEH"
                ,ex.getLocalizedMessage());

        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),"From HttpRequestMethodNotSupportedException in GEH"
                ,ex.getLocalizedMessage());

        return new ResponseEntity<>(customErrorDetails,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException e,WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),e.getMessage()
                ,request.getDescription(false));

        return new ResponseEntity<>(customErrorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException e,
                                                                            WebRequest request){
        CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),e.getMessage()
                ,request.getDescription(false));

        return new ResponseEntity<>(customErrorDetails,HttpStatus.BAD_REQUEST);
    }
}
