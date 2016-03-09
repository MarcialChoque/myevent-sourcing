package com.example.myeventsourcing.transaction.controller;

import com.example.myeventsourcing.common.event.transaction.TransferDetail;
import com.example.myeventsourcing.transaction.service.MoneyTransferService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Administrador on 23/02/2016.
 */

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> transfer(@Validated @RequestBody TransferRequest transferRequest) {
        moneyTransferService.transferMoney(new TransferDetail(
                        transferRequest.sourceAccountId
                        , transferRequest.targetAccountId
                        , transferRequest.amount));
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(null, headers, HttpStatus.ACCEPTED);
    }

    @Data
    public static class TransferRequest {

        @NotNull
        private Long sourceAccountId;

        @NotNull
        private Long targetAccountId;

        @DecimalMin("0.01")
        private BigDecimal amount;
    }
}
