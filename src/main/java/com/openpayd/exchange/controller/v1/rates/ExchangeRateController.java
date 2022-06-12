package com.openpayd.exchange.controller.v1.rates;

import com.openpayd.exchange.dto.ConvertionRate;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.service.IConvertionService;
import com.openpayd.exchange.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/v1/rates")
@Api(value = "exchange rate controller")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final IConvertionService convertionService;

    @GetMapping("/convertion-rate")
    @ApiOperation(value = "convert rate from and to currency")
    public ResponseEntity<JsonResponse> convertionRate(@RequestParam @Size(min=3, max=3) String fromCurrency,
                                                       @RequestParam @Size(min=3, max=3) String toCurrency) throws BaseException {
        ConvertionRate convertionRate = ConvertionRate.builder()
                .fromCurrency(fromCurrency.toUpperCase())
                .toCurrency(toCurrency.toUpperCase())
                .build();
        return JsonResponse.success(convertionService.convertionRate(convertionRate)).toResponseEntity(HttpStatus.OK);
    }
}
