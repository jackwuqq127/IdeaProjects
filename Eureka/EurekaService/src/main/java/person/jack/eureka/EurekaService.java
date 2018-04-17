package person.jack.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer /**声明该启动是一个Eureka服务端*/
public class EurekaService {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaService.class).web(true).run(args);
    }
}
