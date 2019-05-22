package cn.edu.sjtu.ipads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Daniel
 * @since 2019/3/31 21:38
 */
@ApiModel("单个订单中的某个商品信息")
public class OrderItemInfo {
    @ApiModelProperty("商品编号")
    String itemId;
    @ApiModelProperty("店铺编号")
    String storeId;
    @ApiModelProperty("数量")
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
