package app;

import UI.AppWindow;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;


//@SpringBootApplication
public class RPCclient implements AutoCloseable{
//    public static final String directExchangeName = "rpc-exchange";
//    public static final String routingKey = "remoting_key";
//
//    @Autowired
//    RpcHandler rpcClient;
//
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange(directExchangeName);
//    }

//    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(RPCclient.class, args);
//    }

    private Connection connection;
    private static Channel channel;
    private String requestQueueName = "rpc_queue";

    public RPCclient() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");
        factory.setUri("amqps://pssmjwri:o2WA53Fog6fwttbDzRBmZ33kdSiEbISJ@roedeer.rmq.cloudamqp.com/pssmjwri");
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

//    public static void main(String[] argv) {
//
//
//        try (RPCclient client = new RPCclient()) {
//            ClientInterfaceImpl callback = new ClientInterfaceImpl(channel);
//            AppWindow window = new AppWindow(callback);
//            javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                public void run(){
//                    window.createAndShowGUI();
//                }
//            });
//            while(true){
//                System.out.println(" [x] Requesting medication plan");
//                String response = client.call("createMedicationPlan");
//                System.out.println(" [.] Got '" + response + "'");
//                window.removeElements();
//                window.addElements(response);
//                Thread.sleep(10000);
//            }
//        } catch (IOException | TimeoutException | InterruptedException | ParseException | NoSuchAlgorithmException | KeyManagementException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }

    public String call(String message) throws IOException, InterruptedException, ParseException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("rpc-exchange", "remoting_key", props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String processedResult = "";
        JSONParser parser = new JSONParser();
        String unprocessedResult = response.take();
        JSONObject obj = (JSONObject) parser.parse(unprocessedResult);
        JSONArray ja = (JSONArray) obj.get("plan");

        for(Object o : ja){
            JSONObject jo = (JSONObject) o;
            processedResult += jo.get("intake") + "=" + jo.get("medication") + " ";
        }

        channel.basicCancel(ctag);
        return processedResult;
    }

    public void close() throws IOException {
        connection.close();
    }
}
