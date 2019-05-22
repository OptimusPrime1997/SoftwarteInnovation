package cn.edu.sjtu.ipads;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/29 10:24
 */
@ApiModel("订单信息")
public class Order {
    @ApiModelProperty("订单编号")
    private String orderId;
    @ApiModelProperty("创建时间")
    private String dateTime;
    @ApiModelProperty("用户编号")
    private String customerId;
    // itemId -> number
    @ApiModelProperty("所有订单商品")
    private List<OrderItemInfo> items;
    @ApiModelProperty("总价")
    private double totalPrice;
    @ApiModelProperty("配送地址")
    private String deliveryAddress;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemInfo> getItems() {
        return items;
    }

    public void setItems(List<OrderItemInfo> items) {
        this.items = items;
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
