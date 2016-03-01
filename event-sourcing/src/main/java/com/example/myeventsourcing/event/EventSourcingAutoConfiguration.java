package com.example.myeventsourcing.event;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.RabbitListenerConfigUtils;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

/**
 * Created by Administrador on 15/02/2016.
 */

@Configuration
@EnableRabbit
public class EventSourcingAutoConfiguration implements RabbitListenerConfigurer {

    @Bean
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public <E> Event<E> event() {
        return new Event<E>();
    }

    @Bean(name = RabbitListenerConfigUtils.RABBIT_LISTENER_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    @ConditionalOnBean(name = RabbitListenerConfigUtils.RABBIT_LISTENER_ENDPOINT_REGISTRY_BEAN_NAME)
    public RabbitListenerAnnotationBeanPostProcessor rabbitListenerAnnotationProcessor() {
        return new EventListenerAnnotationBeanPostProcessor();
    }

    @Bean
    public org.springframework.messaging.converter.MessageConverter defaultEventMappingConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory eventHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(defaultEventMappingConverter());
        return factory;
    }

    @Bean
    public MessageConverter defaultEventMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(eventHandlerMethodFactory());
    }
}