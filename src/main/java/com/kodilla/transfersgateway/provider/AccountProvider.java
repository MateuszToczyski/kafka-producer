package com.kodilla.transfersgateway.provider;

import com.kodilla.transfersgateway.connector.AccountConnector;
import com.kodilla.transfersgateway.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountProvider {

    private final AccountConnector accountConnector;

    public AccountDto findAccountByNrb(String nrb) {
        return accountConnector.findAccountByNrb(nrb).getAccount();
    }
}
