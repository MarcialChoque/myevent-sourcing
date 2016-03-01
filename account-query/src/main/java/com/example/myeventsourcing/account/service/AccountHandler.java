package com.example.myeventsourcing.account.service;

import com.example.myeventsourcing.common.event.account.AccountOpenedEvent;
import com.example.myeventsourcing.event.Listener;
import org.springframework.stereotype.Service;

/**
 * Created by Administrador on 17/02/2016.
 */

@Service
public class AccountHandler {

    @Listener
    public void handle(AccountOpenedEvent event) {
        System.out.println("ACCOUNT QUERY HANDLE OPENED EVENT: "+event);
    }
}
