package com.example.myeventsourcing.common.event.account;

import com.example.myeventsourcing.common.event.BaseEvent;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrador on 17/02/2016.
 */

@Data
public class AccountOpenedEvent extends BaseEvent {

    private BigDecimal initialBalance;

    public AccountOpenedEvent() {
    }

    public AccountOpenedEvent(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }
}
