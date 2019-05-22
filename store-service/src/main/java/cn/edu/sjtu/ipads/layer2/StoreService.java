package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.SalesResult;
import cn.edu.sjtu.ipads.Store;
import cn.edu.sjtu.ipads.Util;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableSwagger2
public class StoreService {
    Map<String, Store> storeContainer = new ConcurrentHashMap<>();


    @GetMapping("/info")
    void info(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/store/number")
    @ApiOperation("返回店铺的总数")
    public Response<Integer> totalNumber() {
        return new Response<>(storeContainer.size());
    }

    @GetMapping("/store")
    @ApiOperation("获得全部的店铺信息")
    public Response<Collection<Store>> allStore() {
        return new Response<>(storeContainer.values());
    }


    @GetMapping("/store/{storeId}")
    @ApiOperation("获得某个店铺的信息")
    public Response<Store> storeInfo(@ApiParam("店铺编号") @PathVariable String storeId) {
        if (storeContainer.containsKey(storeId)) {
            return new Response<>(storeContainer.get(storeId));
        }
        return new Response<>(-1, "can not find store with id " + storeId);
    }

    @PostMapping("/store")
    @ApiOperation("新增一个店铺")
    public Response<?> addStore(@ApiParam("店铺信息") @RequestBody Store store) {
        store.setLevel(0);
        store.setPublishTime(Util.currentDateTime());
        store.setStoreId(Util.idGenerate("store"));
        store.setTotalItem(0);
        storeContainer.put(store.getStoreId(), store);
        return Response.SUCCESS;
    }

    @PutMapping("/store")
    @ApiOperation("更新一个店铺")
    public Response<?> updateStore(@ApiParam("店铺信息") @RequestBody Store store) {
        storeContainer.put(store.getStoreId(), store);
        return Response.SUCCESS;
    }

    @PutMapping("/store/pubItem/{storeId}")
    @ApiOperation("增加店铺的商品总数")
    public Response<?> publishItem(@ApiParam("店铺编号") @PathVariable String storeId,
                                   @ApiParam("数量") @RequestParam Integer count) {
        storeContainer.get(storeId).addItem(count);
        return Response.SUCCESS;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreService.class, args);
    }

}
