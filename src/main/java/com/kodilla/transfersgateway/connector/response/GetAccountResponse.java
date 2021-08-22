package com.kodilla.transfersgateway.connector.response;

import com.kodilla.transfersgateway.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountResponse {
    private AccountDto account;
}
