package com.example.myeventsourcing.common.event;

import lombok.Data;

/**
 * Created by Administrador on 19/02/2016.
 */

@Data
public class BaseEvent {
    private String id;

    private String type;
}
