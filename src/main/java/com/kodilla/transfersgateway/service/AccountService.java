package com.kodilla.transfersgateway.service;

import com.kodilla.transfersgateway.dto.AccountDto;
import com.kodilla.transfersgateway.provider.AccountProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountProvider accountProvider;

    public AccountDto findByNrb(String nrb) {
        return accountProvider.findAccountByNrb(nrb);
    }
}
