package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.Customer;
import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Daniel
 * @since 2019/3/27 18:40
 */
@RestController
@SpringBootApplication
@EnableEurekaClient
public class BuyerInfoService {
    Map<String, Customer> customerContainer = new ConcurrentHashMap<>();

    private Customer findById(String customerId) {

        return customerContainer.get(customerId);
    }

    @GetMapping("/customer")
    public Response<Collection<Customer>> allCustomer() {
        return new Response<>(customerContainer.values());
    }

    @GetMapping("/customer/number")
    public Response<Integer> totalNumber() {
        return new Response<>(customerContainer.size());
    }

    @GetMapping("/customer/{customerId}")
    public Response<Customer> profile(@PathVariable("customerId") String customerId) {
        Customer customer = findById(customerId);
        if (customer != null) {
            return new Response<>(customer);
        }
        return new Response<>(-1, "can not find customer with id " + customerId);
    }

    @PostMapping("/customer")
    public Response<?> addCustomer(@RequestBody Customer customer) {
        customer.setCustomerId(Util.idGenerate("customer"));
        customer.setLevel(0);
        customer.setCurrentSP(0);
        customerContainer.put(customer.getCustomerId(), customer);
        return Response.SUCCESS;
    }

    @PutMapping("/customer/{customerId}/sp")
    public Response<?> addSP(@PathVariable("customerId") String customerId, @RequestParam Integer sp) {
        Customer customer = customerContainer.get(customerId);
        customer.addSp(sp);
        return Response.SUCCESS;
    }


    public static void main(String[] args) {
        SpringApplication.run(BuyerInfoService.class, args);
    }

}
