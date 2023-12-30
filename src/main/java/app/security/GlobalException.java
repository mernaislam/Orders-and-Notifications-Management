package app.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@Getter
public class GlobalException extends Exception {
    String message;
    HttpStatus httpStatus;

    public GlobalException(String message, HttpStatus status) {
        this.message = message;
        this.httpStatus = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}