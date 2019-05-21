package cn.edu.sjtu.ipads.layer1.services;

import cn.edu.sjtu.ipads.Customer;
import cn.edu.sjtu.ipads.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Component
public class BuyerInfoClient {
    @Autowired
    RestTemplate restTemplate;

    @Value("${buyer-service-name}")
    String SERVICE_NAME = "";

    public int totalCustomer() {
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/customer/number", null);
        Response<Integer> result = restTemplate.getForObject(url, Response.class);
        return result.info;
    }

    public Customer getCustomer(String customerId) {
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/customer/" + customerId, null);
        Response<Map<String, Object>> result = restTemplate.getForObject(url, Response.class);
        return new Customer(result.info);
    }

    public Response addCustomer(Customer customer) {
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/customer", null);
        return restTemplate.postForObject(url, customer, Response.class);
    }

    public boolean addSP(String customerId, int sp) {
        Map<String, String> paras = new HashMap<>();
        paras.put("sp", sp + "");
        String url = URLHelper.getServiceURL(SERVICE_NAME, "/customer/" + customerId + "/sp", paras);
        try {
            restTemplate.put(url, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
