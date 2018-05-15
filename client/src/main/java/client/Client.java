package client;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import java.util.Scanner;

public class Client {

    public static void main(final String... argv) {
        final CachingConnectionFactory cf = new CachingConnectionFactory();
        // set up the listener and container
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);
        final Object listener = new Object() {
            public void handleMessage(final String msg) {
                System.out.println("get => '" + msg + "'");
            }
        };
        final MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("user");
        container.start();
        // send something
        final RabbitTemplate template = new RabbitTemplate(cf);
        final Scanner s = new Scanner(System.in);
        String x;
        while (true) {
            switch (x = s.next()) {
                case "quit":
                    container.stop();
                    System.exit(0);
                    break;
                case "create":
                    System.out.println("create,name,age,email");
                    break;
                default:
                    template.convertAndSend("user", x);
            }
        }
    }

}
