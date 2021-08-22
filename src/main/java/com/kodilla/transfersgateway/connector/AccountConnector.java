package com.kodilla.transfersgateway.connector;

import com.kodilla.transfersgateway.connector.response.GetAccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "accounts")
public interface AccountConnector {

    @GetMapping(value = "/v1/accounts", params = "nrb")
    GetAccountResponse findAccountByNrb(@RequestParam("nrb") String customerId);
}
