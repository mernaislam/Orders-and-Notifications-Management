package app.controllers;

import app.security.GlobalException;
import app.security.ResponseEntityStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ResponseEntityStructure> GlobalException(GlobalException exception) {
        ResponseEntityStructure error = new ResponseEntityStructure();
        error.setMessage(exception.getMessage());
        error.setStatus(exception.getHttpStatus().value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, exception.getHttpStatus());
    }
}
