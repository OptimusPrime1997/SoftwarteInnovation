package cn.edu.sjtu.ipads;


import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/29 10:24
 */
public class Order {
    private String orderId;
    private String dateTime;
    private String customerId;
    // itemId -> number
    private List<OrderItemInfo> items;
    private double totalPrice;
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
