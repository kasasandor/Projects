package ro.tuc.ds2020.messaging;

import java.util.Date;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.tuc.ds2020.entities.Activity;
import ro.tuc.ds2020.repositories.ActivityRepository;

@Component
public class DBMessageConsumer {

	@Autowired
	private ActivityRepository activityRepository;
	
	@RabbitListener(queues = "DBactivity")
	public void recievedMessage(byte[] data){
    	String message = new String(data);
    	String patient_id = "02c34195-caee-4919-b07f-5b68ff5b8f1f";
    	String[] splitData = message.split(" ");
    	Date start = new Date(Long.parseLong(splitData[0]));
    	Date end = new Date(Long.parseLong(splitData[1]));
    	
    	Activity activity = new Activity(patient_id, start, end, splitData[2]);
        System.out.println("DB Saver Recieved Message from RabbitMQ :" + message);
        activityRepository.save(activity);
    }
}
