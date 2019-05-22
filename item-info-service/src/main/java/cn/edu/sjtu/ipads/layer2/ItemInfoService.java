package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.ItemInfo;
import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.Util;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableSwagger2
public class ItemInfoService {
    Map<String, Map<String, ItemInfo>> storeItems = new ConcurrentHashMap<>();
    Map<String, ItemInfo> itemInfoMap = new ConcurrentHashMap<>();


    @GetMapping("/info")
    void info(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/iteminfo/store/{storeId}")
    @ApiOperation("获得店铺的所有商品信息")
    public Response<Map<String, ItemInfo>> storeItems(@PathVariable("storeId") String storeId) {
        Map<String, ItemInfo> result;
        result = storeItems.get(storeId);
        if (result == null)
            result = new HashMap<>();
        return new Response<>(result);
    }

    @PostMapping("/iteminfo/store/{storeId}")
    @ApiOperation("发布一件商品")
    public Response<?> publishItems(@ApiParam("店铺编号") @PathVariable("storeId") String storeId,
                                    @ApiParam("发布的商品信息") @RequestBody List<ItemInfo> items) {
        if (!storeItems.containsKey(storeId)) {
            storeItems.put(storeId, new ConcurrentHashMap<>());
        }
        Map<String, ItemInfo> m = storeItems.get(storeId);
        for (ItemInfo item : items) {
            item.setStoreId(storeId);
            item.setItemId(Util.idGenerate("item"));
            item.setPublishTime(Util.currentDateTime());
            item.setMonthSold(0);
            m.put(item.getItemId(), item);
            itemInfoMap.put(item.getItemId(), item);
        }
        return Response.SUCCESS;
    }

    @PutMapping("/iteminfo/single/{itemId}")
    @ApiOperation("更新一件商品")
    public Response<?> updateItem(@ApiParam("商品编号") @PathVariable("itemId") String itemId,
                                  @ApiParam("商品信息") @RequestBody ItemInfo item) {
        ItemInfo itemInfo = itemInfoMap.get(itemId);
        if (itemInfo == null)
            return Response.FAILED;
        itemInfo.update(item);
        return Response.SUCCESS;
    }

    @PutMapping("/iteminfo/newOrders")
    @ApiOperation("创建一个新的订单")
    public Response<?> newOrders(@ApiParam("订单信息,商品编号->商品数量") @RequestBody Map<String, Integer>
                                         orderInfo) {
        for (String key : orderInfo.keySet()) {
            ItemInfo itemInfo = itemInfoMap.get(key);
            itemInfo.sold(orderInfo.get(key));
        }
        return Response.SUCCESS;
    }

    @PutMapping("/iteminfo/instock")
    @ApiOperation("商品入库,增加商品库存数量")
    public Response<?> inStock(@ApiParam("库存增加信息,商品编号->数量") @RequestBody Map<String, Integer>
                                       stockInfo) {
        for (String key : stockInfo.keySet()) {
            ItemInfo itemInfo = itemInfoMap.get(key);
            itemInfo.instock(stockInfo.get(key));
        }
        return Response.SUCCESS;
    }

    @GetMapping("/iteminfo/single/{itemId}")
    @ApiOperation("获得单个商品信息")
    public Response<?> singleItem(@ApiParam("商品编号") @PathVariable("itemId") String itemId) {
        ItemInfo itemInfo = itemInfoMap.get(itemId);
        if (itemInfo == null)
            return Response.FAILED;
        return new Response<>(itemInfo);
    }


    @GetMapping("/iteminfo")
    @ApiOperation("获得所有的商品信息")
    public Response<Map<String, ItemInfo>> allItems() {
        return new Response<>(itemInfoMap);
    }


    public static void main(String[] args) {
        SpringApplication.run(ItemInfoService.class, args);
    }

}
