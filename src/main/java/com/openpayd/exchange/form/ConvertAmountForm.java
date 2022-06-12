package com.openpayd.exchange.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConvertAmountForm  implements Serializable {

    @NotNull
    @Size(min = 3, max = 3)
    private String fromCurrency;

    @NotNull
    @Size(min = 3, max = 3)
    private String toCurrency;

    @NotNull
    @Min(0)
    private BigDecimal amount;
}
