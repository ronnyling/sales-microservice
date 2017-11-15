package sales;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
@EnableEurekaClient
public class Application {

    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}