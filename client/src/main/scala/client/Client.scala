import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter

import scala.io.StdIn

object run {

  val cf = new CachingConnectionFactory()
  val container = new SimpleMessageListenerContainer(cf)

  val listener = new Object() {
    def handleMessage(msg: String) {
      println(s"get => '$msg'")
    }
  }

  val adapter = new MessageListenerAdapter(listener)
  container.setMessageListener(adapter)
  container.setQueueNames("user")
  container.start()

  val template = new RabbitTemplate(cf)

  while (true) {
    val x = StdIn.readLine()
    x match {
      case "quit" =>
        container.stop()
        System.exit(0)
      case "create" => println("create,name,age,email")
      case _ => template.convertAndSend("user", x)
    }
  }

}
