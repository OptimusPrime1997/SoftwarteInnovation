package cn.edu.sjtu.ipads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel("分析报告")
public class Report {
    @ApiModelProperty("总的用户数量")
    int customerNumber;
    @ApiModelProperty("总的店铺数量")
    int storeNumber;
    @ApiModelProperty("总的订单数量")
    int totalOrder;
    @ApiModelProperty("总的销售数量")
    int totalSales;
    //top 10
    @ApiModelProperty("top 10销量的店铺")
    List<SalesResult> storeRank;
    @ApiModelProperty("top 10购买的用户")
    List<SalesResult> customerRank;
    @ApiModelProperty("top 10销量的商品")
    List<SalesResult> itemRank;
    //24小时的订单数量分析
    @ApiModelProperty("最近24小时的订单数量情况,每小时一次统计")
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

