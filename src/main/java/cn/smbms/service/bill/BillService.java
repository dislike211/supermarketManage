package cn.smbms.service.bill;

import cn.smbms.pojo.Bill;
import cn.smbms.tools.Page;

import java.util.List;
import java.util.Map;

public interface BillService {

    //获得订单列表
   public List<Bill> getBillList();

   //添加bill到数据库中
    public int addBill(Bill bill);

    //通过id实现订单查询功能
    public Bill  getBillById(Integer id);

    //提交修改表单
    public int modifyByBill(Bill bill);

    //删除用户
    public int deleteBillById(Integer bid);

    //获得总记录数
    public int getBillCount(String productName, Integer providerId, Integer isPayment);

    //实现分页
    public List<Bill> getBillList_page(String productName, Integer providerId, Integer isPayment, Integer currentPageNo);

}
