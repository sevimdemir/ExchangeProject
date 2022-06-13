package com.openpayd.exchange.controller.advice;

import com.bugsnag.Bugsnag;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.util.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExchangeExceptionHandler extends ResponseEntityExceptionHandler {

    private final Bugsnag bugsnag;

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<JsonResponse> handleException(BaseException exception) {
        return JsonResponse.failure(exception.getMessage(), exception.getErrorCode()).toResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<JsonResponse> handleException(Exception exception) {
        return JsonResponse.failure(exception.getMessage()).toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
