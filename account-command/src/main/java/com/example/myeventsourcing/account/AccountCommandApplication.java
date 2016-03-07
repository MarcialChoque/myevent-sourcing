package com.example.myeventsourcing.account;

import com.example.myeventsourcing.account.model.Account;
import com.example.myeventsourcing.account.service.AccountService;
import com.example.myeventsourcing.common.SwaggerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

@SpringBootApplication
@Import({SwaggerConfiguration.class})
public class AccountCommandApplication implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(AccountCommandApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Account account = new Account();
        account.setBalance(new BigDecimal("200"));
        accountService.openAccount(account);
    }
}
