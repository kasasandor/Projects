package ro.tuc.ds2020.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Component;
//import ro.tuc.ds2020.entities.Activity;

@Component
public class MessageConsumer {
	
	private static final int ONE_HOUR_IN_MILLIS = 3600000;
	
	@RabbitListener(queues = "activity")
    public void recievedMessage(byte[] data){
    	String activity = new String(data);
    	System.out.println("Rule Checker Recieved Message from RabbitMQ :" + activity);
    	if(activity != null) {
    		checkRules(activity);
    	}
    }

	private void checkRules(String act) {
		String[] splitString = act.split(" ");
    	Long start = Long.parseLong(splitString[0]);
    	Long end = Long.parseLong(splitString[1]);
    	String activity = splitString[2];
    	if(activity.equalsIgnoreCase("sleeping")) {
    		if(end - start > 7 * ONE_HOUR_IN_MILLIS) {
    			System.out.println();
        		System.out.println("Sleep longer then 7 hour");
        		System.out.println();
    			//messagingTemplate.convertAndSend("/topic/messages/info", "Sleep longer then 7 hour");
        	}
    	} else if(activity.equalsIgnoreCase("leaving")) {
    		if(end - start > 5 * ONE_HOUR_IN_MILLIS) {
    			System.out.println();
        		System.out.println("Outdoor activity longer then 5 hour");
        		System.out.println();
        		//messagingTemplate.convertAndSend("/topic/messages/info", "Outdoor activity longer then 5 hour");
        	}
    	} else if(activity.equalsIgnoreCase("toileting") || 
    			  activity.equalsIgnoreCase("showering") ||
    			  activity.equalsIgnoreCase("grooming")) {
    		if(end - start > 0.5 * ONE_HOUR_IN_MILLIS) {
    			System.out.println();
        		System.out.println("Bathroom activity longer then 30 minutes");
        		System.out.println();
        		//messagingTemplate.convertAndSend("/topic/messages/info", "Bathroom activity longer then 30 minutes");
        	}
    	}
    	
	}
}
