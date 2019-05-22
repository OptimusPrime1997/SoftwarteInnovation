package cn.edu.sjtu.ipads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Daniel
 * @since 2019/3/29 9:06
 */
@ApiModel("商品信息")
public class ItemInfo {
    @ApiModelProperty("商品编号")
    private String itemId;
    @ApiModelProperty("商品名称")
    private String name;
    @ApiModelProperty("商品描述")
    private String desp;
    @ApiModelProperty("店铺编号")
    private String storeId;
    @ApiModelProperty("发布日期")
    private String publishTime;
    @ApiModelProperty("价格")
    private Double price;
    @ApiModelProperty("月销量")
    private Integer monthSold;
    @ApiModelProperty("库存数量")
    private Integer left;


    public ItemInfo(Map<String, Object> obj) {
        this.itemId = (String) obj.get("itemId");
        this.name = (String) obj.get("name");
        this.desp = (String) obj.get("desp");
        this.storeId = (String) obj.get("storeId");
        this.publishTime = (String) obj.get("publishTime");
        this.price = (Double) obj.get("price");
        this.monthSold = (Integer) obj.get("monthSold");
        this.left = (Integer) obj.get("left");
    }

    public ItemInfo() {
    }

    public ItemInfo(String itemId, String name, String desp, String storeId, double price, int
            total) {
        this(itemId, name, desp, storeId, new Date().toString(), price, 0, total);
    }

    public ItemInfo(String itemId, String name, String desp, String storeId, String publishTime,
                    double price, int monthSold, int left) {
        this.itemId = itemId;
        this.name = name;
        this.desp = desp;
        this.storeId = storeId;
        this.publishTime = publishTime;
        this.price = price;
        this.monthSold = monthSold;
        this.left = left;
    }

    public boolean update(ItemInfo itemInfo) {
        assert this.getItemId().equals(itemInfo.getItemId());
        this.name = itemInfo.getName();
        this.desp = itemInfo.getDesp();
        this.price = itemInfo.getPrice();
        this.monthSold = itemInfo.getMonthSold();
        this.left = itemInfo.left;
        return true;
    }

    public synchronized void sold(int number) {
        this.monthSold += number;
        this.left -= number;
        assert this.left >= 0;
    }

    public synchronized void instock(int number) {
        this.left += number;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMonthSold() {
        return monthSold;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public void setMonthSold(Integer monthSold) {
        this.monthSold = monthSold;
    }
}
