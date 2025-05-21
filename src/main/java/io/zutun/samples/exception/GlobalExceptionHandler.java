package io.zutun.samples.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.DateTimeException;

@RestControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ApplicationException.class,
    })
    public ResponseEntity<Object> handleApplicationException(ApplicationException ex, WebRequest request) {
        ProblemDetail details = ProblemDetail.forStatusAndDetail(ex.getHttpStatusCode(), ex.getMessage());
        details.setProperty("code", ex.getErrorCode());
        return handleExceptionInternal(
                ex,
                details,
                HttpHeaders.EMPTY,
                ex.getHttpStatusCode(),
                request
        );
    }

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler({
            IllegalStateException.class
    })
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, ex, request);
    }

    @ExceptionHandler({
            ConstraintViolationException.class
    })
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<Object> handleDateTimeException(DateTimeException ex, WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        return handleException(HttpStatus.CONFLICT, ex, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return handleException(HttpStatus.NOT_FOUND, ex, request);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleException(HttpStatus.BAD_REQUEST, ex, request);
    }

    private ResponseEntity<Object> handleException(HttpStatusCode statusCode, Exception ex, WebRequest request) {
        ProblemDetail details = ProblemDetail.forStatusAndDetail(statusCode, ex.getMessage());

        return handleExceptionInternal(
                ex,
                details,
                HttpHeaders.EMPTY,
                statusCode,
                request
        );
    }

}
