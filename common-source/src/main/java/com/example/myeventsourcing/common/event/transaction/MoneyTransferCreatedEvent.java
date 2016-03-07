package com.example.myeventsourcing.common.event.transaction;

import com.example.myeventsourcing.event.BaseEvent;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrador on 23/02/2016.
 */

@Data
public class MoneyTransferCreatedEvent extends BaseEvent {
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;

    public MoneyTransferCreatedEvent() {
    }

    public MoneyTransferCreatedEvent(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
    }
}
