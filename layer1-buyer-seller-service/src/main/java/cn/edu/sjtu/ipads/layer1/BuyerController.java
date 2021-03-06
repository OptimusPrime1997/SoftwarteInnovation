package cn.edu.sjtu.ipads.layer1;

import cn.edu.sjtu.ipads.*;
import cn.edu.sjtu.ipads.layer1.services.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanpenglei
 * @create 2017-12-06 15:26
 **/
@RestController
public class BuyerController {

    @Autowired
    ItemInfoClient itemInfoClient;

    @Autowired
    StoreServiceClient storeServiceClient;

    @Autowired
    BuyerInfoClient buyerInfoClient;

    @Autowired
    OrderServiceClient orderServiceClient;

    @Autowired
    StoreHomepageServiceClient homepageServiceClient;


    /**
     * 查看店铺首页
     * 店铺信息、店铺首页信息
     *
     * @param storeId
     * @return
     */
    @GetMapping("store/{storeId}")
    @ApiOperation("查看店铺首页;包含店铺信息,店铺首页信息;当前用户信息")
    public Response<?> storePage(@ApiParam("店铺编号") @PathVariable("storeId") String storeId,
                                 @ApiParam("用户编号") @RequestParam String
                                         customerId) {
        StoreHomePage homePage = homepageServiceClient.homePage(storeId);
        Customer customer = buyerInfoClient.getCustomer(customerId);
        Map<String, Object> result = new HashMap<>();
        result.put("homePage", homePage);
        result.put("customer", customer);
        return new Response<>(result);
    }

    /**
     * 查看商品详细信息
     * 商品当前的详细信息、店铺信息、买家信息
     *
     * @param itemId
     * @return
     */
    @GetMapping("item/{itemId}")
    @ApiOperation("查看商品详细信息;包含商品当前详细信息,店铺信息,买家信息")
    public Response<?> itemDetailInfo(@ApiParam("商品编号") @PathVariable("itemId") String itemId,
                                      @ApiParam("用户编号") @RequestParam
                                              String customerId) {
        ItemInfo itemInfo = itemInfoClient.singleItem(itemId);
        Store store = storeServiceClient.storeInfo(itemInfo.getStoreId());
        Customer customer = buyerInfoClient.getCustomer(customerId);
        Map<String, Object> result = new HashMap<>();
        result.put("store", store);
        result.put("itemInfo", itemInfo);
        result.put("customer", customer);
        return new Response<>(result);
    }


    /**
     * 购买一件商品
     * 商品信息、订单信息、买家信息
     *
     * @param orderRequest
     * @return
     */
    @PostMapping("order")
    @ApiOperation("购买一件商品")
    public Response<?> newOrder(@ApiParam("请求下单信息") @RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderId(Util.idGenerate("order"));
        order.setCustomerId(orderRequest.getCustomerId());
        order.setDeliveryAddress(orderRequest.getDeliveryAddress());
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setCustomerId(orderRequest.getCustomerId());
        List<OrderItemInfo> orderInfo = orderRequest.getOrderInfo();
        order.setItems(orderInfo);
        //新增订单信息
        Response<?> response = orderServiceClient.newOrder(order);
        if (response.resCode != 0) {
            System.err.println("error add order!");
            return Response.fail("error add order");
        }

        boolean result;
        //更新商品库存信息
        Map<String, Integer> orderInfoMap = new HashMap<>();
        for (OrderItemInfo itemInfo : orderInfo) {
            orderInfoMap.put(itemInfo.getItemId(), itemInfo.getCount());
        }
        result = itemInfoClient.newOrder(orderInfoMap);
        if (!result) {
            System.err.println("error update itemInfo!");
            return Response.fail("error update itemInfo!");
        }

        //更新买家积分
        result = buyerInfoClient.addSP(orderRequest.getCustomerId(), (int) orderRequest.getTotalPrice());
        if (!result) {
            System.err.println("error update sp!");
            return Response.fail("error update sp!");
        }

        return new Response<>(order.getOrderId());
    }


}
