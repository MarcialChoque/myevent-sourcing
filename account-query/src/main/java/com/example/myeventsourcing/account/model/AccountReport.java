package com.example.myeventsourcing.account.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrador on 22/02/2016.
 */

@Entity
public class AccountReport {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
