package cn.edu.sjtu.ipads;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/29 10:02
 */
public class OrderRequest {
    private String customerId;
    // itemId -> number
    private List<OrderItemInfo> orderInfo;
    private double totalPrice;
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
