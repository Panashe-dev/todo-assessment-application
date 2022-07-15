package com.lupart.technologies.TODO.exceptions;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RunTimeExceptionPlaceHolder.class)
    public ResponseEntity<ErrorResponse> handleCustomException(RunTimeExceptionPlaceHolder ex) {

        ErrorResponse errorResponse = populateErrorResponse("400", ex.getLocalizedMessage(), ex.getMessage(), LocalDateTime.now());

        log.error("Something went wrong, Exception : " + ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(InvalidFormatException ex) {

        ErrorResponse errorResponse = populateErrorResponse("400", ex.getLocalizedMessage(), ex.getMessage(), LocalDateTime.now());
        log.error("Something went wrong, Exception : " + ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(TodoNotFoundException ex) {

        ErrorResponse errorResponse = populateErrorResponse("404",
                ex.getLocalizedMessage(), ex.getMessage(), LocalDateTime.now());
        log.error("Something went wrong, Exception : " + ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {

        ErrorResponse errorResponse = populateErrorResponse("500",
                ex.getLocalizedMessage(), ex.getMessage(), LocalDateTime.now());
        log.error("Something went wrong, Exception : " + ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }




    public ErrorResponse populateErrorResponse(String code, String message, String errors, LocalDateTime timestamp) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorId(UUID.randomUUID());

        Error error = new Error();
        error.setStatus(code);
        error.setError(errors);
        error.setMessage(message);
        error.setTimestamp(timestamp);

        errorResponse.setErrors(Collections.singletonList(error));

        return errorResponse;
    }
}