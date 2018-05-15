package com.orange.ivdev.formation.spring.formationspringcrudmq.MQReceiver;

import com.orange.ivdev.formation.spring.formationspringcrudmq.Entity.User;
import com.orange.ivdev.formation.spring.formationspringcrudmq.Sender.UserSender;
import com.orange.ivdev.formation.spring.formationspringcrudmq.Service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RabbitListener(queues="user")
public class UserReceiver {

    @Autowired
    UserSender userSender;

    @Autowired
    UserService userService;

    @RabbitHandler
    public void receive(final String in) {
        System.out.println(" [x] Received '" + in + "'");
        final var paramList = Arrays.asList(in.split(","));
        if (paramList.size() == 1) {
            final List<User> val = userService.all();
            final var listString = val.stream().map(e -> e.getId() + " : " + e.getName() + ", " + e.getAge())
                .collect(Collectors.joining("\n"));
            userSender.send(listString);
        }
        else if (paramList.size() == 2) {
            final var present = userService.byId(Integer.valueOf(paramList.get(1))).isPresent();
            if (!present) userSender.send("User not found with id " + Integer.valueOf(paramList.get(1)));
            else {
                final var fUser = userService.byId(Integer.valueOf(paramList.get(1))).get();
                userSender.send(fUser.getId() + " : " + fUser.getName() + ", " + fUser.getAge());
            }
        }
        else if (paramList.size() == 4) {
            final User user = new User();
            user.setAge(Integer.valueOf(paramList.get(2)));
            user.setName(paramList.get(1));
            user.setEmail(paramList.get(3));
            userService.save(user);
            final List<User> val = userService.all();
            final var listString = val.stream().map(e -> e.getId() + " : " + e.getName() + ", " + e.getAge())
                    .collect(Collectors.joining("\n"));
            userSender.send(listString);
        }
    }

}
