package com.openpayd.exchange.controller.v1.transaction;

import com.openpayd.exchange.dto.ExchangeTransaction;
import com.openpayd.exchange.exception.BaseException;
import com.openpayd.exchange.form.ConvertAmountForm;
import com.openpayd.exchange.service.IConvertionService;
import com.openpayd.exchange.service.IExchangeTransactionService;
import com.openpayd.exchange.util.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/v1/transaction")
@Api(value = "exchange transaction controller")
@RequiredArgsConstructor
public class ExchangeTransactionController {

    private final IConvertionService convertionService;

    private final IExchangeTransactionService exchangeTransactionService;

    @PostMapping("/convertion-amount")
    @ApiOperation(value = "convert rate from and to currency")
    public ResponseEntity<JsonResponse> convertionAmount(@RequestBody @Validated ConvertAmountForm convertAmountForm) throws BaseException {
        ExchangeTransaction exchangeTransaction = ExchangeTransaction.builder()
                .fromCurrency(convertAmountForm.getFromCurrency().toUpperCase())
                .toCurrency(convertAmountForm.getToCurrency().toUpperCase())
                .amount(convertAmountForm.getAmount())
                .build();
        return JsonResponse.success(convertionService.convertionAmount(exchangeTransaction)).toResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/list")
    @ApiOperation(value = "get transaction list")
    public ResponseEntity<JsonResponse> getTransactions(@RequestParam(required = false) Long transactionId,
                                                        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date transactionDate,
                                                        @RequestParam(required = false) Integer page,
                                                        @RequestParam(required = false) Integer size) throws BaseException {
        if (transactionId != null) {
            return JsonResponse.success(exchangeTransactionService.findByTransactionId(transactionId)).toResponseEntity(HttpStatus.OK);
        } else if (transactionDate != null) {
            Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
            return JsonResponse.success(exchangeTransactionService.findAllByTransactionDate(transactionDate, pageable)).toResponseEntity(HttpStatus.OK);
        }
        return JsonResponse.failure("Transaction id or date must be provided").toResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
