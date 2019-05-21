package cn.edu.sjtu.ipads.layer1.services;

import cn.edu.sjtu.ipads.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * 描述: 指定这个接口所要调用的 提供者服务名称 "eureka-provider"
 *
 * @author yanpenglei
 * @create 2017-12-06 15:13
 **/
@Component
public class OrderServiceClient {
    @Autowired
    RestTemplate restTemplate;

    @Value("${order-service-name}")
    String SERVICE_NAME = "";

    public int totalOrder() {
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/order/number", null);
        Response<Integer> result = restTemplate.getForObject(url, Response.class);
        return result.info;
    }

    public List<Map<String, Object>> getSalesResult(String type) {
        assert type.equals("store") || type.equals("customer") || type.equals("item");
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/order/rank/" + type, null);
        Response<List<Map<String, Object>>> response =
                restTemplate.getForObject(url, Response.class);
        return response.info;
    }

    public Response<?> newOrder(Order Order) {
        String url = "http://" + SERVICE_NAME + "/order";
        return restTemplate.postForEntity(url, Order, Response.class).getBody();
    }
}
