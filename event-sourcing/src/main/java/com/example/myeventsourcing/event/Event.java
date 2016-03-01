package com.example.myeventsourcing.event;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

public class Event<E> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private MessageConverter defaultEventMessageConverter;

    public void fire(E event) {
        EventSourcingConfig config = EventSourcingConfig.create(event.getClass());
        amqpAdmin.declareExchange(config.getExchange());
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        rabbitTemplate.setMessageConverter(defaultEventMessageConverter);
        rabbitTemplate.convertAndSend(config.getExchangeName(), null, event);
        rabbitTemplate.setMessageConverter(messageConverter);
    }
}
