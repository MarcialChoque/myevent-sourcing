package com.example.myeventsourcing.transaction.service;

import com.example.myeventsourcing.common.event.account.AccountDebitFailedDueToInsufficientFundsEvent;
import com.example.myeventsourcing.common.event.account.AccountDebitedEvent;
import com.example.myeventsourcing.common.event.transaction.DebitRecordedEvent;
import com.example.myeventsourcing.common.event.transaction.MoneyTransferCreatedEvent;
import com.example.myeventsourcing.common.event.transaction.TransferDetail;
import com.example.myeventsourcing.event.Event;
import com.example.myeventsourcing.event.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrador on 23/02/2016.
 */

@Service
public class MoneyTransferService {

    @Autowired
    private Event<MoneyTransferCreatedEvent> transferCreatedEventEvent;

    @Autowired
    private Event<DebitRecordedEvent> debitRecordedEventEvent;

    @Transactional
    public void transferMoney(TransferDetail detail) {
        //todo init
        System.out.println("INIT TRANSFER MONEY: "+detail);
        fireMoneyTransferCreatedEvent(detail);
    }

    @Listener
    public void recordDebit(AccountDebitedEvent e) {
        System.out.println("STARTING RECORD DEBIT: "+e);
        debitRecordedEventEvent.fire(new DebitRecordedEvent());
    }

    @Listener
    public void recordDebitFailed(AccountDebitFailedDueToInsufficientFundsEvent e) {
        System.out.println("STARTING RECORD DEBIT FAILED: "+e);
    }

    private void fireMoneyTransferCreatedEvent(TransferDetail detail) {
        MoneyTransferCreatedEvent event = new MoneyTransferCreatedEvent(
                detail.getSourceAccountId()
                , detail.getTargetAccountId()
                , detail.getAmount());
        transferCreatedEventEvent.fire(event);
    }
}
