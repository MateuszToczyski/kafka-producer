package com.kodilla.transfersgateway.service;

import com.kodilla.commons.Transfer;
import com.kodilla.commons.TransferMessage;
import com.kodilla.transfersgateway.dto.AccountDto;
import com.kodilla.transfersgateway.exception.InsufficientBalanceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferProducer {

    private static final String TRANSFERS_TOPIC = "transfers";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final AccountService accountService;

    public void sendTransfer(final Transfer transfer) {
        AccountDto senderAccount = accountService.findByNrb(transfer.getSenderAccount());
        if (!isBalanceSufficient(senderAccount, transfer)) {
            throw new InsufficientBalanceException("Insufficient balance for transfer: " + transfer);
        }
        TransferMessage transferMessage = new TransferMessage(transfer);
        log.info("Sending message to Kafka, transferMessage: {}", transferMessage);
        kafkaTemplate.send(TRANSFERS_TOPIC, transferMessage);
        log.info("Message was sent");
    }

    public boolean isBalanceSufficient(AccountDto account, Transfer transfer) {
        return account.getAvailableFunds().compareTo(transfer.getAmount()) >= 0;
    }

}