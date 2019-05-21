package cn.edu.sjtu.ipads.layer1;

import cn.edu.sjtu.ipads.*;
import cn.edu.sjtu.ipads.layer1.services.BuyerInfoClient;
import cn.edu.sjtu.ipads.layer1.services.ItemInfoClient;
import cn.edu.sjtu.ipads.layer1.services.OrderServiceClient;
import cn.edu.sjtu.ipads.layer1.services.StoreServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/29 9:57
 */
@RestController
public class SellerController {
    @Autowired
    private BuyerInfoClient buyerInfoClient;

    @Autowired
    private ItemInfoClient itemInfoClient;

    @Autowired
    private StoreServiceClient storeServiceClient;

    @Autowired
    private OrderServiceClient orderServiceClient;

    /**
     * 分析报告
     *
     * @return
     */
    @GetMapping(value = "/report")
    public Response<Report> report() {
        Report report = new Report();
        int customerNumber = buyerInfoClient.totalCustomer();
        int storeNumber = storeServiceClient.totalStore();
        int orderNumber = orderServiceClient.totalOrder();
        report.setCustomerNumber(customerNumber);
        report.setStoreNumber(storeNumber);
        List<Map<String, Object>> storeSalesResultMap = orderServiceClient.getSalesResult("store");
        List<Map<String, Object>> customerSalesResultMap = orderServiceClient.getSalesResult
                ("customer");
        List<Map<String, Object>> itemSalesResultMap = orderServiceClient.getSalesResult("item");

        List<SalesResult> storeSalesResult = new ArrayList<>();
        List<SalesResult> customerSalesResult = new ArrayList<>();
        List<SalesResult> itemSalesResult = new ArrayList<>();

        for (Map<String, Object> re : storeSalesResultMap) {
            SalesResult salesResult = new SalesResult(re);
            salesResult.setDataObject(storeServiceClient.storeInfo(salesResult.getKey()));
            storeSalesResult.add(salesResult);
        }

        for (Map<String, Object> re : customerSalesResultMap) {
            SalesResult salesResult = new SalesResult(re);
            salesResult.setDataObject(buyerInfoClient.getCustomer(salesResult.getKey()));
            customerSalesResult.add(salesResult);
        }

        for (Map<String, Object> re : itemSalesResultMap) {
            SalesResult salesResult = new SalesResult(re);
            salesResult.setDataObject(itemInfoClient.singleItem(salesResult.getKey()));
            itemSalesResult.add(salesResult);
        }

        report.setStoreRank(storeSalesResult);
        report.setCustomerRank(customerSalesResult);
        report.setItemRank(itemSalesResult);

        report.setTotalOrder(orderNumber);

        return new Response<>(report);
    }

    @PutMapping(value = "/publishItem")
    public Response<?> publishItems(@RequestBody List<ItemInfo> itemInfos, @RequestParam String
            storeId) {

        Response res = itemInfoClient.publishItem(storeId, itemInfos);
        boolean res1 = storeServiceClient.pubItem(storeId, itemInfos.size());
        return res.resCode == 0 && res1 ? Response.SUCCESS : Response.FAILED;
    }



}
