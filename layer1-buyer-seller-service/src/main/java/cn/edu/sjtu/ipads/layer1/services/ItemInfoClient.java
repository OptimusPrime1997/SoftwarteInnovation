package cn.edu.sjtu.ipads.layer1.services;

import cn.edu.sjtu.ipads.ItemInfo;
import cn.edu.sjtu.ipads.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 描述: 指定这个接口所要调用的 提供者服务名称 "eureka-provider"
 *
 * @author yanpenglei
 * @create 2017-12-06 15:13
 **/
@Component
public class ItemInfoClient {
    @Autowired
    RestTemplate restTemplate;

    @Value("${item-service-name}")
    String SERVICE_NAME = "";


    public Response<?> publishItem(String storeId, List<ItemInfo> items) {
        String url = "http://" + SERVICE_NAME + "/iteminfo/store/" + storeId;
        return restTemplate.postForEntity(url, items, Response.class).getBody();
    }

    public ItemInfo singleItem(String itemId) {
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/iteminfo/single/" + itemId,
                null);
        Response<Map<String, Object>> res = restTemplate.getForObject(url, Response.class);
        ItemInfo itemInfo = new ItemInfo(res.info);
        return itemInfo;
    }

    public boolean newOrder(Map<String, Integer> orderInfo) {
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/iteminfo/newOrders",
                null);
        try {
            restTemplate.put(url, orderInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean inStock(Map<String, Integer> stockInfo) {
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/iteminfo/instock",
                null);
        try {
            restTemplate.put(url, stockInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
