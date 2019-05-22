package cn.edu.sjtu.ipads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/29 10:02
 */
@ApiModel("下单请求信息")
public class OrderRequest {
    @ApiModelProperty("用户编号")
    private String customerId;
    // itemId -> number
    @ApiModelProperty("订单商品信息")
    private List<OrderItemInfo> orderInfo;
    @ApiModelProperty("总价")
    private double totalPrice;
    @ApiModelProperty("地址")
    private String deliveryAddress;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemInfo> getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(List<OrderItemInfo> orderInfo) {
        this.orderInfo = orderInfo;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
