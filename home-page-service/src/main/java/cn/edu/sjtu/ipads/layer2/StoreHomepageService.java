package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.Store;
import cn.edu.sjtu.ipads.StoreHomePage;
import cn.edu.sjtu.ipads.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class StoreHomepageService {
    Map<String, StoreHomePage> storeContainer = new ConcurrentHashMap<>();


    @GetMapping("/store")
    public Response<Collection<StoreHomePage>> allPages() {
        return new Response<>(storeContainer.values());
    }

    @GetMapping("/store/{storeId}")
    public Response<StoreHomePage> homepage(@PathVariable String storeId) {
        if (storeContainer.containsKey(storeId)) {
            return new Response<>(storeContainer.get(storeId));
        }
        return new Response<>(-1, "can not find store with id " + storeId);
    }

    @PostMapping("/store")
    public Response<?> addStore(@RequestBody StoreHomePage homePage) {
        storeContainer.put(homePage.getStoreId(), homePage);
        return Response.SUCCESS;
    }

    @PutMapping("/store")
    public Response<?> updateHomePage(@RequestBody StoreHomePage homePage) {
        storeContainer.put(homePage.getStoreId(), homePage);
        return Response.SUCCESS;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreHomepageService.class, args);
    }

}
