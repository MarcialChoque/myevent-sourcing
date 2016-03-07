package com.example.myeventsourcing.event.repository;

import com.example.myeventsourcing.event.BaseEvent;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import java.util.Date;

/**
 * Created by Administrador on 03/03/2016.
 */
public class EventListener extends AbstractMongoEventListener<BaseEvent> {

    //changes in onBeforeSave doesn't work
    //mongo doesn't save null fields.
    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseEvent> event) {
        if (event.getSource().getCreatedDate() == null) {
            event.getSource().setCreatedDate(new Date());
        }
    }
}