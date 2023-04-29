package com.pro.rabbitmgdemo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.pro.rabbitmgdemo.config.MessagingConfig;
import com.pro.rabbitmgdemo.dto.OrderStatus;

@Component
public class User {
	
	@RabbitListener(queues= MessagingConfig.queue)
	public void messageFromQueue(OrderStatus orderStatus) {
		
		System.out.println("message recived from queue"+ orderStatus);
	}

}
