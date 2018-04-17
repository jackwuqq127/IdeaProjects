package person.jack;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppOne {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AppOne.class).web(true).run(args);
    }
}
