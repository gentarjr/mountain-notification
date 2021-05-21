package com.mountain.notif.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import winterfell.domain.ErrCode;
import winterfell.domain.ResponseEnvelope;
import winterfell.domain.ResponseEnvelopeV2;
import winterfell.exceptions.WinterfellException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.FileNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ResponseEnvelopeV2<?>> handleConstraintViolationException(ConstraintViolationException ex) {
        ConstraintViolation<?> violation = ex.getConstraintViolations().stream().findFirst().orElse(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseEnvelopeV2<>(String.valueOf(ErrCode.ERR_NOTFOUND.getCode()), violation != null ? violation.getMessage() : "Bad Request"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    protected ResponseEntity<ResponseEnvelope> handleFileNotFoundException(FileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseEnvelope(ErrCode.ERR_NOTFOUND.getCode(), ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(WinterfellException.class)
    protected ResponseEntity<ResponseEnvelope> handleServiceException(WinterfellException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseEnvelope(ex.getErrCode().getCode(), ex.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseEnvelope> handleGeneralException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseEnvelope(ErrCode.ERR_UNKNOWN.getCode(), ErrCode.ERR_UNKNOWN.getMessage()));
    }
}
