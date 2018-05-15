package com.orange.ivdev.formation.spring

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter

fun main(args: Array<String>) {

    val cf = CachingConnectionFactory()
    val container = SimpleMessageListenerContainer(cf)
    val listener = object : Any() {

        fun handleMessage(msg: String) {
            println("get => '$msg'")
        }

    }

    val adapter = MessageListenerAdapter(listener)
    container.setMessageListener(adapter)
    container.setQueueNames("user")
    container.start()

    // REPL
    val template = RabbitTemplate(cf)
    loop@ while (true) {
        val x = readLine()!!
        when (x) {
            "quit" -> break@loop
            "create" -> println("create,name,age,email")
            else -> template.convertAndSend("user", x)
        }
    }
    container.stop()
    System.exit(0)
}

