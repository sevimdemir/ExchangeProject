package com.openpayd.exchange.controller.advice;

import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.util.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExchangeExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<JsonResponse> handleException(Exception exception) {
        return JsonResponse.failure(exception.getMessage()).toResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
