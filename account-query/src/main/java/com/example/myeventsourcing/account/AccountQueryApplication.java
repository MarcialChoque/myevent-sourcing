package com.example.myeventsourcing.account;

import com.example.myeventsourcing.event.Listener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrador on 16/02/2016.
 */

@SpringBootApplication
public class AccountQueryApplication {

    @Listener
    public void handle(String event) {
        System.out.println("EVENT LISTENER : "+event);
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountQueryApplication.class, args);
    }
}
