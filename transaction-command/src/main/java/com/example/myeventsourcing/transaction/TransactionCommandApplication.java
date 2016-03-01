package com.example.myeventsourcing.transaction;

import com.example.myeventsourcing.common.SwaggerConfiguration;
import com.example.myeventsourcing.common.event.transaction.TransferDetail;
import com.example.myeventsourcing.transaction.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

/**
 * Created by Administrador on 23/02/2016.
 */

@SpringBootApplication
@Import({SwaggerConfiguration.class})
public class TransactionCommandApplication implements CommandLineRunner {

    @Autowired
    private MoneyTransferService moneyTransferService;

    public static void main(String[] args) {
        SpringApplication.run(TransactionCommandApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        moneyTransferService.transferMoney(new TransferDetail(1L, 2L, new BigDecimal(1000)));
    }
}
