package com.bridgelabz.bookstore.utility;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.entity.User;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("${bookstore.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${bookstore.rabbitmq.routingkey}")
	private String routingkey;	
	
	public void send(User company) {
		rabbitTemplate.convertAndSend(exchange, routingkey, company);
		System.out.println("Send msg = " + company);
	    
	}
}