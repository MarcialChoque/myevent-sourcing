package com.example.myeventsourcing.account.service.repo;

import com.example.myeventsourcing.account.model.AccountReport;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrador on 22/02/2016.
 */
public interface AccountReportRepository extends JpaRepository<AccountReport, Long> {

}
