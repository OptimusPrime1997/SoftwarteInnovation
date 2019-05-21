package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.ItemInfo;
import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

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
public class ItemInfoService {
    Map<String, Map<String, ItemInfo>> storeItems = new ConcurrentHashMap<>();
    Map<String, ItemInfo> itemInfoMap = new ConcurrentHashMap<>();

    @GetMapping("/iteminfo/store/{storeId}")
    public Response<Map<String, ItemInfo>> storeItems(@PathVariable("storeId") String storeId) {
        Map<String, ItemInfo> result;
        result = storeItems.get(storeId);
        if (result == null)
            result = new HashMap<>();
        return new Response<>(result);
    }

    @PostMapping("/iteminfo/store/{storeId}")
    public Response<?> publishItems(@PathVariable("storeId") String storeId,
                                    @RequestBody List<ItemInfo> items) {
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
    public Response<?> updateItem(@PathVariable("itemId") String itemId, @RequestBody ItemInfo item) {
        ItemInfo itemInfo = itemInfoMap.get(itemId);
        if (itemInfo == null)
            return Response.FAILED;
        itemInfo.update(item);
        return Response.SUCCESS;
    }

    @PutMapping("/iteminfo/newOrders")
    public Response<?> newOrders(@RequestBody Map<String, Integer> orderInfo) {
        for (String key : orderInfo.keySet()) {
            ItemInfo itemInfo = itemInfoMap.get(key);
            itemInfo.sold(orderInfo.get(key));
        }
        return Response.SUCCESS;
    }

    @PutMapping("/iteminfo/instock")
    public Response<?> inStock(@RequestBody Map<String, Integer> stockInfo) {
        for (String key : stockInfo.keySet()) {
            ItemInfo itemInfo = itemInfoMap.get(key);
            itemInfo.instock(stockInfo.get(key));
        }
        return Response.SUCCESS;
    }

    @GetMapping("/iteminfo/single/{itemId}")
    public Response<?> singleItem(@PathVariable("itemId") String itemId) {
        ItemInfo itemInfo = itemInfoMap.get(itemId);
        if (itemInfo == null)
            return Response.FAILED;
        return new Response<>(itemInfo);
    }


    @GetMapping("/iteminfo")
    public Response<Map<String, ItemInfo>> allItems() {
        return new Response<>(itemInfoMap);
    }


    public static void main(String[] args) {
        SpringApplication.run(ItemInfoService.class, args);
    }

}
