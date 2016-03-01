package com.example.myeventsourcing.account.service.repo;

import com.example.myeventsourcing.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrador on 17/02/2016.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

}
