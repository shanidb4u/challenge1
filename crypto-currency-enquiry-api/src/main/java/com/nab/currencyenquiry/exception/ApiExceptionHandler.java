package com.nab.currencyenquiry.exception;

import com.nab.currencyenquiry.domain.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiError> handleIllegalArgumentException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiError.builder()
                        .location("crypto-currency-enquiry-api")
                        .message(ex.getMessage())
                        .severity("Low")
                        .build());

    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ApiError> handleMissingRequestHeaderException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiError.builder()
                        .location("crypto-currency-enquiry-api")
                        .message(ex.getMessage())
                        .severity("Low")
                        .build());

    }



    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ApiError> handleNotFoundException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiError.builder()
                        .location("crypto-currency-enquiry-api")
                        .message(ex.getMessage())
                        .severity("Low")
                        .build());

    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ApiError> handleBadRequestException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiError.builder()
                        .location("crypto-currency-enquiry-api")
                        .message(ex.getMessage())
                        .severity("Low")
                        .build());

    }

    @ExceptionHandler(ApiGeneralException.class)
    protected ResponseEntity<ApiError> handleApiGeneralException(ApiGeneralException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiError.builder()
                        .location("crypto-currency-enquiry-api")
                        .message(ex.getMessage())
                        .severity("Medium")
                        .build());

    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handleException(Exception ex) {
        log.error("Exception while retrieving data ..." + ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiError.builder()
                        .location("crypto-currency-enquiry-api")
                        .message("Generic Error, Please Contact Admin")
                        .severity("High")
                        .build());

    }

}
