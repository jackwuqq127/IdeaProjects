package person.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import person.jack.servlet.TestServlet;

@SpringBootApplication
public class App {
    /**将TesrServlet 注册到IOC 容器！*/
    @Bean
    public ServletRegistrationBean testServlet(){
        return new ServletRegistrationBean(new TestServlet());
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
