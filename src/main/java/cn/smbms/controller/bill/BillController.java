package cn.smbms.controller.bill;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.Page;
import cn.smbms.tools.PageSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/sys/bill")
public class BillController {

    private static final Logger logger = Logger.getLogger(BillController.class);

    @Autowired
    private BillService billService;

    @Autowired
    private ProviderService providerService;

    /**
     * 获得订单列表
     * @param model
     * @param productName
     * @param providerId
     * @param isPayment
     * @param currentPageNo
     * @return bill/billlist
     */
    @RequestMapping({"/billlist.html","/bill.do"})
    public String getBillList(Model model,
                              @RequestParam(value = "queryProductName", required = false) String productName,
                              @RequestParam(value = "queryProviderId", required = false) Integer providerId,
                              @RequestParam(value = "queryIsPayment", required = false)  Integer isPayment,
                              @RequestParam(value = "pageIndex", defaultValue = "1") Integer currentPageNo){

        logger.debug("enter BillController---》getBillList method");


        //获得订单列表
//        List<Bill> billList = this.billService.getBillList();

        List<Bill> billList = this.billService.getBillList_page(productName,providerId,isPayment,currentPageNo);
        model.addAttribute("billList",billList);

        //分页
        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(Constants.pageSize);

        int totalCount = this.billService.getBillCount(productName,providerId,isPayment);
        pageSupport.setTotalCount(totalCount);
        model.addAttribute("pageSupport",pageSupport);


        //保存输入框中的信息
        model.addAttribute("queryProductName",productName);
        model.addAttribute("queryProviderId",providerId);
        model.addAttribute("queryIsPayment",isPayment);

        //获得providerList供应商列表
        List<Provider> providerList = this.providerService.getProviderList();
        model.addAttribute("providerList",providerList);

        logger.debug("leave BillController---》getBillList method");

        return "bill/billlist";
    }

    /**
     * 显示订单添加页面
     * @param model
     * @return bill/billadd
     */
    @RequestMapping(value = "/billadd.html")
    public String addBill(Model model){
        logger.debug("enter BillController---》addBill method");

        List<Provider> providerList = this.providerService.getProviderList();
        model.addAttribute("providerList",providerList);

        logger.debug("leave BillController---》addBill method");
        return "bill/billadd";
    }

    /**
     * 提交添加订单的表单
     * @param bill
     * @param session
     * @return redirect:/sys/bill/billlist.html
     */
    @RequestMapping("/billaddsave.html")
    public String saveaddBill(Bill bill,
                              HttpSession session){
        logger.debug("enter BillController---》saveaddBill method");

        //创建者
        User loginUser = (User) session.getAttribute(Constants.USER_SESSION);
        //设置创建人
        bill.setCreatedBy(loginUser.getId());
        //设置创建时间-当前时间
        bill.setCreationDate(new Date());

        int count =   this.billService.addBill(bill);

        logger.debug("leave BillController---》saveaddBill method");
        if(count>0){
            return "redirect:/sys/bill/billlist.html";
        }else{
            return "bill/billadd";
        }
    }


    /**
     * 通过id实现订单查询功能
     * @param id
     * @param model
     * @return bill/billview
     */
    @RequestMapping("/billview.html")
    public String viewBill(@RequestParam(value = "bid") Integer id,
                           Model model){
        logger.debug("enter BillController---》viewBill method");

        Bill bill = this.billService.getBillById(id);
        model.addAttribute("bill",bill);

        logger.debug("leave BillController---》viewBill method");
        return "bill/billview";
    }


    /**
     * 显示订单修改界面
     * @param bid
     * @param model
     * @return bill/billmodify
     */
    @RequestMapping("/billmodify.html")
    public String modifyBill(@RequestParam(value = "bid") Integer bid,
                             Model model){
        logger.debug("enter BillController---》modifyBill method");

        List<Provider> providerList = this.providerService.getProviderList();
        model.addAttribute("providerList",providerList);


        Bill bill = this.billService.getBillById(bid);
        model.addAttribute("bill",bill);

        logger.debug("leave BillController---》modifyBill method");
        return "bill/billmodify";
    }


    /**
     * 提交修改表单
     * @param bill
     * @return redirect:/sys/bill/billlist.html
     */
    @RequestMapping("/billmodifysave.html")
    public String saveBillModify(Bill bill,
                                 Model model,
                                 HttpSession session){
        logger.debug("enter BillController---》saveBillModify method");

        //修改者
        User ModifyUser = (User) session.getAttribute(Constants.USER_SESSION);
        //设置修改人
        bill.setModifyBy(ModifyUser.getId());
        //设置修改时间-当前时间
        bill.setModifyDate(new Date());

        int count =  this.billService.modifyByBill(bill);

        List<Provider> providerList = this.providerService.getProviderList();
        model.addAttribute("providerList",providerList);

        logger.debug("leave BillController---》saveBillModify method");
        if(count>0){
            return "redirect:/sys/bill/billlist.html";
        }else{
            return "bill/billmodify" ;
        }
    }


    /**
     * 删除订单
     * @param bid
     * @return redirect:/sys/bill/billlist.html
     */
    @RequestMapping("/billdelete.html")
    public String deleteBill(@RequestParam(value = "bid") Integer bid){
        logger.debug("enter BillController---》deleteBill method");
       int count = this.billService.deleteBillById(bid);
       if(count>0){
           logger.debug("删除成功");
       }else{
           logger.debug("删除失败");
       }
       return "redirect:/sys/bill/billlist.html";
    }
}
