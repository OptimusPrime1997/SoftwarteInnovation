package cn.edu.sjtu.ipads;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/31 16:54
 */
public class StoreHomePage {
    String storeId;
    List<String> itemIds;
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
