package com.example.myeventsourcing.event;

import com.example.myeventsourcing.event.repository.EventRepository;
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

    @Autowired
    private EventRepository eventRepository;

    public void fire(E event) {
        EventSourcingConfig config = EventSourcingConfig.create(event.getClass());
        amqpAdmin.declareExchange(config.getExchange());
        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        rabbitTemplate.setMessageConverter(defaultEventMessageConverter);
        rabbitTemplate.convertAndSend(config.getExchangeName(), null, event);
        E result = event;
        if(event instanceof BaseEvent) {
            result = (E) eventRepository.save(event);
        }
        rabbitTemplate.setMessageConverter(messageConverter);
    }
}
