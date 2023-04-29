package com.pro.rabbitmgdemo.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.rabbitmgdemo.config.MessagingConfig;
import com.pro.rabbitmgdemo.dto.Order;
import com.pro.rabbitmgdemo.dto.OrderStatus;

@RestController
@RequestMapping("/order")

public class OrderPublisher {
	
	@Autowired
	private RabbitTemplate template;
	
	
	@PostMapping("/{resturentname}")
	public String bookorder(@RequestBody Order order,@PathVariable String resturentname) {
		order.setOrderid(UUID.randomUUID().toString());
		
		
		
		OrderStatus orderStatus = new OrderStatus(order, "process", "order placed sucessfully"+resturentname);
		
		template.convertAndSend(MessagingConfig.exchange, MessagingConfig.Routing_Key, orderStatus);
		
		return "sucess!!";
	}

}
