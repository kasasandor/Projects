package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class HessianConfig {

    @Bean(name = "/hello")
    RemoteExporter sayHelloServiceHessian() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(new HelloWorldImpl());
        exporter.setServiceInterface(HelloWorld.class);
        return exporter;
    }
}
