package com.example.myeventsourcing.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrador on 04/03/2016.
 */

@RestController
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final ExecutorService customObservableExecutor = Executors.newFixedThreadPool(10);

    @RequestMapping(value = "/testAccount", method = RequestMethod.GET)
    public Observable<String> testAccount() {
        return Observable.<String>create(s -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                //Ignore
            }
            //s.onNext(s);
            System.out.println("SOUT TEEEEEEEESSSSSSSSTTT");
        });//.subscribeOn(Schedulers.from(customObservableExecutor));
    }
}
