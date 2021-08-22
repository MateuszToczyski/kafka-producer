package com.kodilla.transfersgateway.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String recipientAccount;
    private String senderAccount;
    private String title;
    private BigDecimal amount;
}
