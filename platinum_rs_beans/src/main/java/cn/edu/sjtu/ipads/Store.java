package cn.edu.sjtu.ipads;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/29 10:28
 */
public class Store {
    private String storeId;
    private String name;
    private String desp;
    private String publishTime;
    private Integer totalItem;
    private Integer level;

    public Store() {
    }

    public Store(Map<String, Object> des) {
        this.name = (String) des.get("name");
        this.desp = (String) des.get("desp");
        this.storeId = (String) des.get("storeId");
        this.publishTime = (String) des.get("publishTime");
        this.totalItem = (Integer) des.get("totalItem");
        this.level = (Integer) des.get("level");
    }

    public synchronized void addItem(int number) {
        this.totalItem += number;
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

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public void setLevel(Integer level) {
        this.level = level;
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


    public int getTotalItem() {
        return totalItem;
    }


    public int getLevel() {
        return level;
    }

}
