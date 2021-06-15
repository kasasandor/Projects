package app;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.UUID;

public class ClientInterfaceImpl implements ClientInterface{

    private Channel channel;
    private String requestQueueName = "rpc_queue";

    public ClientInterfaceImpl(Channel channel){
        this.channel = channel;
    }
    public void medicationTaken(String medicationName) throws IOException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        String message = "Medication " + medicationName + " taken!";
        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
    }

    public void medicationNotTaken(String medicationName) throws IOException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        String message = "Medication " + medicationName + " not taken!";
        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
    }
}
