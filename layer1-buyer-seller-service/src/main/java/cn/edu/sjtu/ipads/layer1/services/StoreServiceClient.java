package cn.edu.sjtu.ipads.layer1.services;

import cn.edu.sjtu.ipads.ItemInfo;
import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: 指定这个接口所要调用的 提供者服务名称 "eureka-provider"
 *
 * @author yanpenglei
 * @create 2017-12-06 15:13
 **/
@Component
public class StoreServiceClient {
    @Autowired
    RestTemplate restTemplate;

    @Value("${store-service-name}")
    String SERVICE_NAME = "";

    public int totalStore() {
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/store/number", null);
        Response<Integer> result = restTemplate.getForObject(url, Response.class);
        return result.info;
    }

    public Store storeInfo(String storeId) {
        String url = "http://" + SERVICE_NAME + "/store/" + storeId;
        Response<Map<String, Object>> res = restTemplate.getForObject(url, Response.class);
        Store store = new Store(res.info);
        return store;
    }

    public boolean pubItem(String storeId, Integer count) {
        Map<String, String> paras = new HashMap();
        paras.put("count", count + "");
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/store/pubItem/" + storeId, paras);
        try {
            restTemplate.put(url, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean newStore(Store store) {
        String url = "http://" + SERVICE_NAME + "/store";
        Response<?> res = restTemplate.postForObject(url, store, Response.class);
        return res.resCode == 0;
    }

    public boolean updateStore(Store store) {
        String url = "http://" + SERVICE_NAME + "/store";
        try {
            restTemplate.put(url, store);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
