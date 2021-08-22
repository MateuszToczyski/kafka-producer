package com.kodilla.transfersgateway.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private final Long id;
    private final String nrb;
    private final String currency;
    private final BigDecimal availableFunds;
    private final Long customerId;
}
