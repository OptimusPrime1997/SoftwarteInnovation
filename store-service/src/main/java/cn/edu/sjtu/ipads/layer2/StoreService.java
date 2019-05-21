package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.SalesResult;
import cn.edu.sjtu.ipads.Store;
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
public class StoreService {
    Map<String, Store> storeContainer = new ConcurrentHashMap<>();

    @GetMapping("/store/number")
    public Response<Integer> totalNumber() {
        return new Response<>(storeContainer.size());
    }

    @GetMapping("/store")
    public Response<Collection<Store>> allStore() {
        return new Response<>(storeContainer.values());
    }


    @GetMapping("/store/{storeId}")
    public Response<Store> storeInfo(@PathVariable String storeId) {
        if (storeContainer.containsKey(storeId)) {
            return new Response<>(storeContainer.get(storeId));
        }
        return new Response<>(-1, "can not find store with id " + storeId);
    }

    @PostMapping("/store")
    public Response<?> addStore(@RequestBody Store store) {
        store.setLevel(0);
        store.setPublishTime(Util.currentDateTime());
        store.setStoreId(Util.idGenerate("store"));
        store.setTotalItem(0);
        storeContainer.put(store.getStoreId(), store);
        return Response.SUCCESS;
    }

    @PutMapping("/store")
    public Response<?> updateStore(@RequestBody Store store) {
        storeContainer.put(store.getStoreId(), store);
        return Response.SUCCESS;
    }

    @PutMapping("/store/pubItem/{storeId}")
    public Response<?> publishItem(@PathVariable String storeId, @RequestParam Integer count) {
        storeContainer.get(storeId).addItem(count);
        return Response.SUCCESS;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreService.class, args);
    }

}
