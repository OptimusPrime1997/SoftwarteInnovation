package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.Store;
import cn.edu.sjtu.ipads.StoreHomePage;
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
public class StoreHomepageService {
    Map<String, StoreHomePage> storeContainer = new ConcurrentHashMap<>();


    @GetMapping("/info")
    void info(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/store")
    @ApiOperation("获得所有的店铺信息")
    public Response<Collection<StoreHomePage>> allPages() {
        return new Response<>(storeContainer.values());
    }

    @GetMapping("/store/{storeId}")
    @ApiOperation("获得某个店铺的主页信息")
    public Response<StoreHomePage> homepage(@PathVariable String storeId) {
        if (storeContainer.containsKey(storeId)) {
            return new Response<>(storeContainer.get(storeId));
        }
        return new Response<>(-1, "can not find store with id " + storeId);
    }

    @PostMapping("/store")
    @ApiOperation("新增一个店铺主页信息")
    public Response<?> addStore(@ApiParam("店铺主页信息") @RequestBody StoreHomePage homePage) {
        storeContainer.put(homePage.getStoreId(), homePage);
        return Response.SUCCESS;
    }

    @PutMapping("/store")
    @ApiOperation("更新店铺主页信息")
    public Response<?> updateHomePage(@ApiParam("店铺主页信息")@RequestBody StoreHomePage homePage) {
        storeContainer.put(homePage.getStoreId(), homePage);
        return Response.SUCCESS;
    }

    public static void main(String[] args) {
        SpringApplication.run(StoreHomepageService.class, args);
    }

}
