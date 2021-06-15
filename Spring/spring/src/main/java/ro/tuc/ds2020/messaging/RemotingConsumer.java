package ro.tuc.ds2020.messaging;

import com.rabbitmq.client.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Configuration
public class RemotingConsumer{ //implements Runnable{

    @RabbitListener(queues = "remotingQueue")
    public String recievedMessage(String data){
        //String activity = new String(data);
        System.out.println("RPC queue Recieved Message from RabbitMQ :" + data);
        return "Recieved from Server";
    }
//    private static final String RPC_QUEUE_NAME = "rpc_queue";
//
//    public void run() {
//        ConnectionFactory factory = new ConnectionFactory();
//    //asd
//        try {
//            factory.setUri("amqps://pssmjwri:o2WA53Fog6fwttbDzRBmZ33kdSiEbISJ@roedeer.rmq.cloudamqp.com/pssmjwri");
//
//            Connection connection = factory.newConnection();
//            Channel channel = connection.createChannel();
//            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
//            channel.queuePurge(RPC_QUEUE_NAME);
//
//            channel.basicQos(1);
//
//            System.out.println(" [x] Awaiting RPC requests");
//
//            Object monitor = new Object();
//            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
//                        .Builder()
//                        .correlationId(delivery.getProperties().getCorrelationId())
//                        .build();
//
//                JSONObject response = new JSONObject();
//
//                try {
//                    String message = new String(delivery.getBody(), "UTF-8");
//
//                    if(!message.startsWith("Medication")){
//                        Method method
//                                = RemotingConsumer.class.getMethod(message);
//                        RemotingConsumer instance = new RemotingConsumer();
//                        JSONObject result = (JSONObject) method.invoke(this);
//                        System.out.println(result);
//                        response = result;
//                    }
//
//
//                    System.out.println(" [.] " + message);
//
//                } catch (RuntimeException | NoSuchMethodException e) {
//                    System.out.println(" [.] " + e.toString());
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } finally {
//                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.toString().getBytes("UTF-8"));
//                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//
//                    synchronized (monitor) {
//                        monitor.notify();
//                    }
//                }
//            };
//
//            channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> { }));
//            // Wait and be prepared to consume the message from RPC client.
//            while (true) {
//                synchronized (monitor) {
//                    try {
//                        monitor.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public JSONObject createMedicationPlan(){
        JSONArray ja = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("intake", "15:00-16:00");
        obj.put("medication", new Medication("Paracetamol", "10mg"));
        ja.put(obj);
        JSONObject obj1 = new JSONObject();
        obj1.put("intake", "16:00-17:00");
        obj1.put("medication", new Medication("Propranolol", "10mg"));
        ja.put(obj1);
        JSONObject obj2 = new JSONObject();
        obj2.put("intake", "18:00-19:00");
        obj2.put("medication", new Medication("Xarelto", "15mg"));
        ja.put(obj2);

        JSONObject mainObj = new JSONObject();
        mainObj.put("plan", ja);

        return mainObj;
    }
}
