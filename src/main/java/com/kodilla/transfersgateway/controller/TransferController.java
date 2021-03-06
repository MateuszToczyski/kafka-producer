package com.kodilla.transfersgateway.controller;

import com.kodilla.commons.Transfer;
import com.kodilla.transfersgateway.controller.request.TransferRequest;
import com.kodilla.transfersgateway.exception.InsufficientBalanceException;
import com.kodilla.transfersgateway.exception.SenderAccountNotFoundException;
import com.kodilla.transfersgateway.service.TransferProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferProducer transferProducer;

    @PostMapping
    public void saveTransfer(@RequestBody TransferRequest request) {
        log.info("Received transfer request: {}", request);
        Transfer transfer = new Transfer();
        transfer.setAmount(request.getAmount());
        transfer.setRecipientAccount(request.getRecipientAccount());
        transfer.setSenderAccount(request.getSenderAccount());
        transfer.setTitle(request.getTitle());

        try {
            transferProducer.sendTransfer(transfer);
        } catch (SenderAccountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender account not found");
        } catch (InsufficientBalanceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
