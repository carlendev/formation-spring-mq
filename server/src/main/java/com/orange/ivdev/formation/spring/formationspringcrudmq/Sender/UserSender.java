package com.orange.ivdev.formation.spring.formationspringcrudmq.Sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;

    public void send(final String msg) {
        this.template.convertAndSend(queue.getName(), msg);
        System.out.println(" [x] Sent '" + msg + "'");
    }

}
