package com.orange.ivdev.formation.spring.formationspringcrudmq.Queue;

import com.orange.ivdev.formation.spring.formationspringcrudmq.MQReceiver.UserReceiver;
import com.orange.ivdev.formation.spring.formationspringcrudmq.Sender.UserSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMQ {

    @Bean
    public Queue user() {
        return new Queue("user");
    }

    @Bean
    public UserReceiver receiver() {
        return new UserReceiver();
    }

    @Bean
    public UserSender sender() {
        return new UserSender();
    }

}
