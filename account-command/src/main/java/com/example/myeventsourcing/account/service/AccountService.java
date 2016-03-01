package com.example.myeventsourcing.account.service;

import com.example.myeventsourcing.account.model.Account;
import com.example.myeventsourcing.account.service.repo.AccountRepository;
import com.example.myeventsourcing.common.event.account.AccountDebitFailedDueToInsufficientFundsEvent;
import com.example.myeventsourcing.common.event.account.AccountDebitedEvent;
import com.example.myeventsourcing.common.event.account.AccountOpenedEvent;
import com.example.myeventsourcing.common.event.transaction.DebitRecordedEvent;
import com.example.myeventsourcing.common.event.transaction.MoneyTransferCreatedEvent;
import com.example.myeventsourcing.event.Event;
import com.example.myeventsourcing.event.Listener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by Administrador on 17/02/2016.
 */

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private Event<AccountOpenedEvent> accountOpenedEventEvent;

    @Autowired
    private Event<AccountDebitFailedDueToInsufficientFundsEvent> accountDebitFailedDueToInsufficientFundsEventEvent;

    @Autowired
    private Event<AccountDebitedEvent> accountDebitedEventEvent;

    @Transactional
    public void openAccount(Account account) {
        Account newAccount = repository.save(account);
        fireAccountOpenedEvent(newAccount);
    }

    private void fireAccountOpenedEvent(Account account) {
        AccountOpenedEvent event = new AccountOpenedEvent();
        event.setId(account.getId()+"");
        event.setType("AccountOpenedEvent");
        event.setInitialBalance(account.getBalance());
        accountOpenedEventEvent.fire(event);
    }

    @Listener
    public void debitAccount(MoneyTransferCreatedEvent e) {
        System.out.println("STARTING DEBIT ACCOUNT : "+e);
        BigDecimal amount = loadAmount(e.getSourceAccountId());

        if(amount.compareTo(e.getAmount()) < 0) {
            accountDebitFailedDueToInsufficientFundsEventEvent.fire(new AccountDebitFailedDueToInsufficientFundsEvent());
        } else {
            //todo debit
            accountDebitedEventEvent.fire(new AccountDebitedEvent());
        }
    }

    @Listener
    public void creditAccount(DebitRecordedEvent e) {
        System.out.println("STARTING CREDIT ACCOUNT : "+e);
        //todo credit
    }

    private BigDecimal loadAmount(Long accountId) {
        //Account account = repository.getOne(accountId);
        //return account.getBalance();
        return new BigDecimal(400);
    }
}
