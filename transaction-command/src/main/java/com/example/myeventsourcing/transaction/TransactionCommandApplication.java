package com.example.myeventsourcing.transaction;

import com.example.myeventsourcing.common.SwaggerConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by Administrador on 23/02/2016.
 */

@SpringBootApplication
@Import({SwaggerConfiguration.class})
public class TransactionCommandApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TransactionCommandApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
