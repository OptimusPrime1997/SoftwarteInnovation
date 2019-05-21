package cn.edu.sjtu.sfino.servicecenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ServicecenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicecenterApplication.class, args);
    }

}
