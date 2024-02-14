package com.ost.ecommerce.error.controller;

import com.ost.ecommerce.error.controller.dto.ApiErrorMessage;
import com.ost.ecommerce.error.exceptions.NotFoundException;
import com.ost.ecommerce.error.exceptions.OperationNotValidException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorMessage> notFound(NotFoundException ex) {
        return new ResponseEntity<>(new ApiErrorMessage(ex.messageId, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(OperationNotValidException.class)
    public ResponseEntity<ApiErrorMessage> notValid(OperationNotValidException ex) {
        return new ResponseEntity<>(new ApiErrorMessage(ex.messageId, ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
}
