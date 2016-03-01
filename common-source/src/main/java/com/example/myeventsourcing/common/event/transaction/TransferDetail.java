package com.example.myeventsourcing.common.event.transaction;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrador on 23/02/2016.
 */

@Data
public class TransferDetail {

    private Long sourceAccountId;

    private Long targetAccountId;

    private BigDecimal amount;

    public TransferDetail() {
    }

    public TransferDetail(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
    }
}
