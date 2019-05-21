package cn.edu.sjtu.ipads;

/**
 * @author Daniel
 * @since 2019/3/31 21:38
 */
public class OrderItemInfo {
    String itemId;
    String storeId;
    Integer count;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
