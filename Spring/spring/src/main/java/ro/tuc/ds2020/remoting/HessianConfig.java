package ro.tuc.ds2020.remoting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class HessianConfig {
    @Bean(name = "/remoting")
    RemoteExporter sayHelloServiceHessian() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(new RemoteServiceImpl());
        exporter.setServiceInterface(RemoteService.class);
        return exporter;
    }
}
