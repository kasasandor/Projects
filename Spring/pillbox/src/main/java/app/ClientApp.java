package app;

import UI.AppWindow;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@EnableScheduling
public class ClientApp {

    private static ConfigurableApplicationContext context;
    private static AppWindow appWindow;
    private static RemoteService service;

    @Bean
    public AppWindow appWindow(RemoteService service){
        return new AppWindow(service);
    }

    @Bean
    public HessianProxyFactoryBean hessianInvoker() {
        HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8080/remoting");
        invoker.setServiceInterface(RemoteService.class);
        return invoker;
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 500)
    public void getPlan() throws ParseException {
        String response = service.sendMedicationPlan();
        String processedResult = "";
        JSONParser parser = new JSONParser();

        JSONObject obj = (JSONObject) parser.parse(response);
        JSONArray ja = (JSONArray) obj.get("plan");

        for(Object o : ja){
            JSONObject jo = (JSONObject) o;
            processedResult += jo.get("intake") + "=" + jo.get("medication") + " ";
        }

        System.out.println(processedResult);
        appWindow.removeElements();
        appWindow.addElements(processedResult);
    }

    public static void main(String[] args) {

        context = new SpringApplicationBuilder(ClientApp.class).headless(false).run(args);
        appWindow = context.getBean(AppWindow.class);
        service = context.getBean(RemoteService.class);
    }
}
