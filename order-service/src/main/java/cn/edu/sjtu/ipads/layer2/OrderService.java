package cn.edu.sjtu.ipads.layer2;

import cn.edu.sjtu.ipads.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class OrderService {
    Map<String, Order> orderContainer = new ConcurrentHashMap<>();
    Map<String, Map<String, Order>> customerOrder = new ConcurrentHashMap<>();
    Map<String, Map<String, Order>> storeOrder = new ConcurrentHashMap<>();
    Map<String, Map<String, Order>> itemOrder = new ConcurrentHashMap<>();

    @GetMapping("/order/number")
    public Response<Integer> totalNumber() {
        return new Response<>(orderContainer.size());
    }

    @GetMapping("/order/rank/customer")
    public Response<List<SalesResult>> customerRank() {
        PriorityQueue<SalesResult> p = new PriorityQueue<>(new Comparator<SalesResult>() {
            @Override
            public int compare(SalesResult o1, SalesResult o2) {
                //最大堆
                return o2.getTotal().compareTo(o1.getTotal());
            }
        });
        for (String customer : customerOrder.keySet()) {
            Map<String, Order> co = customerOrder.get(customer);
            double total = 0;
            for (Order o : co.values()) {
                total += o.getTotalPrice();
            }
            p.add(new SalesResult(customer, total));
        }
        List<SalesResult> result = new ArrayList<>();
        for (int i = 0; i < 10 && !p.isEmpty(); i++) {
            result.add(p.poll());
        }
        return new Response<>(result);
    }


    @GetMapping("/order/rank/store")
    public Response<List<SalesResult>> storeRank() {
        PriorityQueue<SalesResult> p = new PriorityQueue<>(new Comparator<SalesResult>() {
            @Override
            public int compare(SalesResult o1, SalesResult o2) {
                //最大堆
                return o2.getTotal().compareTo(o1.getTotal());
            }
        });
        for (String store : storeOrder.keySet()) {
            Map<String, Order> co = storeOrder.get(store);
            double total = 0;
            for (Order o : co.values()) {
                total += o.getTotalPrice();
            }
            p.add(new SalesResult(store, total));
        }
        List<SalesResult> result = new ArrayList<>();
        for (int i = 0; i < 10 && !p.isEmpty(); i++) {
            result.add(p.poll());
        }
        return new Response<>(result);
    }

    @GetMapping("/order/rank/item")
    public Response<List<SalesResult>> itemRank() {
        PriorityQueue<SalesResult> p = new PriorityQueue<>(new Comparator<SalesResult>() {
            @Override
            public int compare(SalesResult o1, SalesResult o2) {
                //最大堆
                return o2.getTotal().compareTo(o1.getTotal());
            }
        });
        for (String item : itemOrder.keySet()) {
            Map<String, Order> co = itemOrder.get(item);
            double total = 0;
            for (Order o : co.values()) {
                total += o.getTotalPrice();
            }
            p.add(new SalesResult(item, total));
        }
        List<SalesResult> result = new ArrayList<>();
        for (int i = 0; i < 10 && !p.isEmpty(); i++) {
            result.add(p.poll());
        }
        return new Response<>(result);
    }


    @GetMapping("/order")
    public Response<Collection<Order>> allOrders() {
        return new Response<>(orderContainer.values());
    }

    @PostMapping("/order")
    public Response<?> addOrder(@RequestBody Order order) {
        order.setDateTime(Util.currentDateTime());

        orderContainer.put(order.getOrderId(), order);
        List<OrderItemInfo> orderInfo = order.getItems();
        for (OrderItemInfo item : orderInfo) {
            String customerId = order.getCustomerId();
            if (!customerOrder.containsKey(customerId))
                customerOrder.put(order.getCustomerId(), new ConcurrentHashMap<>());
            String itemId = item.getItemId();
            if (!itemOrder.containsKey(itemId)) {
                itemOrder.put(itemId, new ConcurrentHashMap<>());
            }
            String storeId = item.getStoreId();
            if (!storeOrder.containsKey(storeId)) {
                storeOrder.put(storeId, new ConcurrentHashMap<>());
            }
            customerOrder.get(customerId).put(order.getOrderId(), order);
            itemOrder.get(itemId).put(order.getOrderId(), order);
            storeOrder.get(storeId).put(order.getOrderId(), order);

        }

        return Response.SUCCESS;
    }

    @GetMapping("/order/customer/{customerId}")
    public Response<Collection<Order>> customerOrder(@PathVariable("customerId") String customerId) {
        if (customerOrder.containsKey(customerId))
            return new Response<>(customerOrder.get(customerId).values());
        return new Response<>(new ArrayList<>());
    }

    @GetMapping("/order/item/{itemId}")
    public Response<Collection<Order>> itemOrder(@PathVariable("itemId") String itemId) {
        if (itemOrder.containsKey(itemId))
            return new Response<>(itemOrder.get(itemId).values());
        return new Response<>(new ArrayList<>());
    }

    @GetMapping("/order/range/{startTime}/{endTime}")
    public Response<List<Order>> findRangeOrder(@PathVariable("startTime") String startTime,
                                                @PathVariable("endTime") String endTime) {
        return Response.SUCCESS;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderService.class, args);
    }

}
