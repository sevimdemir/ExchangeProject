package com.openpayd.exchange.controller.v1.transaction;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@Api(value = "exchange transaction controller")
@RequiredArgsConstructor
public class ExchangeTransactionController {
}
