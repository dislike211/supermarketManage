package cn.smbms.dao.bill;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {

    //获得订单列表
    public List<Bill> getBillList();

    //添加新订单
    public int addBill(Bill bill);

    //通过id实现订单查询功能
    public  Bill getBillById(@Param("id") Integer id);

    //提交修改表单
    public int modifyByBill(Bill bill);

    //删除订单
    public int deleteBillById(@Param("id") Integer bid);

    //实现分页查询订单列表
    public List<Bill> geBilltListPage(@Param("productName") String productName,
                                      @Param("providerId") Integer providerId,
                                      @Param("isPayment") Integer isPayment,
                                      @Param("from") int from,
                                      @Param("pageSize") int pageSize);

    //<!--获得订单总记录数:通过商品名称,供应商id和是否支付-->
    public int getBillCountByProduceNameAndProviderIdAndIsPayment(@Param("productName") String productName,
                                                                  @Param("providerId") Integer providerId,
                                                                  @Param("isPayment") Integer isPayment);
    
}
