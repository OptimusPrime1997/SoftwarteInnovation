package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.Customer;
import cn.edu.sjtu.ipads.Response;
import cn.edu.sjtu.ipads.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
@EnableSwagger2
public class BuyerInfoService {
    Map<String, Customer> customerContainer = new ConcurrentHashMap<>();

    private Customer findById(String customerId) {

        return customerContainer.get(customerId);
    }


    @GetMapping("/info")
    void info(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/customer")
    @ApiOperation("获得所有的用户信息")
    public Response<Collection<Customer>> allCustomer() {
        return new Response<>(customerContainer.values());
    }

    @GetMapping("/customer/number")
    @ApiOperation("返回总的用户数量")
    public Response<Integer> totalNumber() {
        return new Response<>(customerContainer.size());
    }

    @GetMapping("/customer/{customerId}")
    @ApiOperation("返回单个用户的个人信息")
    public Response<Customer> profile(@ApiParam("用户编号") @PathVariable("customerId")
                                              String customerId) {
        Customer customer = findById(customerId);
        if (customer != null) {
            return new Response<>(customer);
        }
        return new Response<>(-1, "can not find customer with id " + customerId);
    }

    @PostMapping("/customer")
    @ApiOperation("新增一个用户")
    public Response<?> addCustomer(@ApiParam("用户信息") @RequestBody Customer customer) {
        customer.setCustomerId(Util.idGenerate("customer"));
        customer.setLevel(0);
        customer.setCurrentSP(0);
        customerContainer.put(customer.getCustomerId(), customer);
        return Response.SUCCESS;
    }

    @PutMapping("/customer/{customerId}/sp")
    @ApiOperation("增加用户经验值")
    public Response<?> addSP(@ApiParam("用户编号") @PathVariable("customerId") String customerId
            , @RequestParam @ApiParam("经验值") Integer sp) {
        Customer customer = customerContainer.get(customerId);
        customer.addSp(sp);
        return Response.SUCCESS;
    }


    public static void main(String[] args) {
        SpringApplication.run(BuyerInfoService.class, args);
    }

}
