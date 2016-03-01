package com.example.myeventsourcing.event;

/**
 * Created by Administrador on 18/02/2016.
 */
public abstract class BaseEvent {
    private String id;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
