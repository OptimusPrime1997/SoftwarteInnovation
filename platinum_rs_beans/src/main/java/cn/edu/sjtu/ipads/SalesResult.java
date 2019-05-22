package cn.edu.sjtu.ipads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/31 21:28
 */
@ApiModel("销售统计结果")
public class SalesResult {
    @ApiModelProperty("统计对象的编号")
    String key;
    @ApiModelProperty("统计对象的信息")
    Object dataObject;
    @ApiModelProperty("总量")
    Double total;

    public SalesResult(Map<String, Object> res) {
        this.key = (String) res.get("key");
        this.total = (Double) res.get("total");
    }


    public SalesResult() {
    }

    public SalesResult(String key, Double total) {
        this.key = key;
        this.total = total;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Object getDataObject() {
        return dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }
}
