package io.zutun.samples.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    private final ApplicationError error;

    public ApplicationException(ApplicationError error) {
        super(error.getDefaultMessage());
        this.error = error;
    }

    public ApplicationException(ApplicationError error, Throwable cause) {
        super(error.getDefaultMessage(), cause);
        this.error = error;
    }

    public HttpStatus getHttpStatusCode() {
        return this.error.getStatus();
    }

    public String getErrorCode() {
        return this.error.getCode();
    }

}