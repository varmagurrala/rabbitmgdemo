package com.pro.rabbitmgdemo.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {
	
	public static final String queue="varma_queue";
	public static final String exchange="varma_queue";
	public static final String Routing_Key="varma_queue";

	@Bean
	public Queue queue() {
		
		return new Queue(queue);
	}
	
	@Bean
	public TopicExchange exchange() {
		 return new TopicExchange(exchange);
	}
	
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(Routing_Key);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter( );
	}
	
	@Bean
		public AmqpTemplate template(ConnectionFactory connectionFactory) {
			final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
			rabbitTemplate.setMessageConverter(converter());
			
			return rabbitTemplate;
			
		
	}
}
