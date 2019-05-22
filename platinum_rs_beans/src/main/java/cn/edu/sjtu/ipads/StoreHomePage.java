package cn.edu.sjtu.ipads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/31 16:54
 */
@ApiModel("店铺主页")
public class StoreHomePage {
    @ApiModelProperty("店铺编号")
    String storeId;
    @ApiModelProperty("店铺所有商品编号")
    List<String> itemIds;
    @ApiModelProperty("店铺主页的数据信息")
    String homePageData;

    public StoreHomePage() {
    }

    public StoreHomePage(Map<String, Object> des) {
        this.storeId = (String) des.get("storeId");
        this.itemIds = (List<String>) des.get("itemIds");
        this.homePageData = (String) des.get("homePageData");
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public String getHomePageData() {
        return homePageData;
    }

    public void setHomePageData(String homePageData) {
        this.homePageData = homePageData;
    }
}
