package ro.tuc.ds2020.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class MessagingConfig {

	public static final String topicExchangeName = "activity-queue";
	public static final String directExchangeName = "rpc-exchange";

	static final String queueName = "activity";
	static final String DBqueueName = "DBactivity";
	static final String RemotingQueueName = "remotingQueue";

	// Rule checker queue
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("routingkey");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(MessageConsumer receiver) {
		return new MessageListenerAdapter(receiver, "recievedMessage");
	}

	// DB Queue
	@Bean
	Queue DBqueue() {
		return new Queue(DBqueueName, false);
	}
	
	@Bean
	Binding DBbinding(TopicExchange exchange) {
		return BindingBuilder.bind(DBqueue()).to(exchange).with("routingkey");
	}
	
	@Bean
	SimpleMessageListenerContainer DBcontainer(ConnectionFactory connectionFactory,
			MessageListenerAdapter DBListenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(DBqueueName);
		container.setMessageListener(DBListenerAdapter);
		return container;
	}
	
	@Bean
	MessageListenerAdapter DBListenerAdapter(DBMessageConsumer receiver) {
		return new MessageListenerAdapter(receiver, "recievedMessage");
	}
}
