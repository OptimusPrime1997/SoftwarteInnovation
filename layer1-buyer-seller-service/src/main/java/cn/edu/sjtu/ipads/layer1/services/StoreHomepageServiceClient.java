package cn.edu.sjtu.ipads.layer1.services;

import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.Store;
import cn.edu.sjtu.ipads.StoreHomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 描述: 指定这个接口所要调用的 提供者服务名称 "eureka-provider"
 *
 * @author yanpenglei
 * @create 2017-12-06 15:13
 **/
@Component
public class StoreHomepageServiceClient {
    @Autowired
    RestTemplate restTemplate;

    @Value("${homepage-service-name}")
    String SERVICE_NAME = "";

    public StoreHomePage homePage(String storeId) {
        String url = "http://" + SERVICE_NAME + "/store/" + storeId;
        Response<Map<String, Object>> res = restTemplate.getForObject(url, Response.class);
        StoreHomePage homePage = new StoreHomePage(res.info);
        return homePage;
    }

    public boolean newHomePage(StoreHomePage storeHomePage) {
        String url = "http://" + SERVICE_NAME + "/store";
        Response<?> res = restTemplate.postForObject(url, storeHomePage, Response.class);
        return res.resCode == 0;
    }

    public boolean updateHomePage(StoreHomePage homePage) {
        String url = "http://" + SERVICE_NAME + "/store";
        try {
            restTemplate.put(url, homePage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
