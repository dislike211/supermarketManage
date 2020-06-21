package cn.smbms.service.bill;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.tools.Constants;
import cn.smbms.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillMapper billMapper;

    //获得订单列表
    public List<Bill> getBillList() {

        return this.billMapper.getBillList();
    }


    //添加订单到数据库中
    @Override
    public int addBill(Bill bill) {
        return this.billMapper.addBill(bill);
    }

    //查看订单信息
    @Override
    public Bill getBillById(Integer id) {

        return this.billMapper.getBillById(id);
    }

    //提交修改表单
    @Override
    public int modifyByBill(Bill bill) {
        return this.billMapper.modifyByBill(bill);
    }

    //删除用户
    @Override
    public int deleteBillById(Integer bid) {
        return this.billMapper.deleteBillById(bid);
    }

    //获得订单总记录数:通过商品名称,供应商id和是否支付
    @Override
    public int getBillCount(String productName, Integer providerId, Integer isPayment) {
        return this.billMapper.getBillCountByProduceNameAndProviderIdAndIsPayment(productName,providerId,isPayment);
    }


    //实现分页查询订单列表
    @Override
    public List<Bill> getBillList_page(String productName, Integer providerId, Integer isPayment, Integer currentPageNo) {
        int pageSize = Constants.pageSize;
        int from = (currentPageNo-1)*pageSize;
        List<Bill> billList = this.billMapper.geBilltListPage(productName,providerId,isPayment,from,pageSize);
        return billList;
    }



}
