package cn.edu.sjtu.ipads.layer1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
@RestController
@RefreshScope
public class Layer1Application {

    @Value("${key}")
    private String key;



    @GetMapping("/key")
    String key() {
        return key;
    }


    @GetMapping("/info")
    void info(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Bean
    @LoadBalanced
    RestTemplate initRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Layer1Application.class, args);
    }
}
