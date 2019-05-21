package cn.edu.sjtu.ipads;

import java.util.List;

/**
 * @author Daniel
 * @since 2019/3/29 9:38
 * 报表：
 * 总的用户数量
 * 总的店铺数量
 * 总的订单数
 * 总的销售额
 * 店铺销售额排行
 * 用户消费排行
 * 订单时段分析
 */
public class Report {
    int customerNumber;
    int storeNumber;
    int totalOrder;
    int totalSales;
    //top 10
    List<SalesResult> storeRank;
    List<SalesResult> customerRank;
    List<SalesResult> itemRank;
    //24小时的订单数量分析
    int [] dayOrderHist;


    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public List<SalesResult> getStoreRank() {
        return storeRank;
    }

    public void setStoreRank(List<SalesResult> storeRank) {
        this.storeRank = storeRank;
    }

    public List<SalesResult> getCustomerRank() {
        return customerRank;
    }

    public void setCustomerRank(List<SalesResult> customerRank) {
        this.customerRank = customerRank;
    }

    public List<SalesResult> getItemRank() {
        return itemRank;
    }

    public void setItemRank(List<SalesResult> itemRank) {
        this.itemRank = itemRank;
    }

    public int[] getDayOrderHist() {
        return dayOrderHist;
    }

    public void setDayOrderHist(int[] dayOrderHist) {
        this.dayOrderHist = dayOrderHist;
    }
}

