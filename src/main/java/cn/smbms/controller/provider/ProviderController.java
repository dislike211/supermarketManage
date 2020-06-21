package cn.smbms.controller.provider;

import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys/provider")
public class ProviderController {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(ProviderController.class);

    @Autowired
    private ProviderService providerService;

    /**
     * 获得供应商列表
     * @param model
     * @return provider/providerlist
     */
    @RequestMapping({"/providerlist.html","/provider.do"})
    public String getProviderList(Model model,
                                  @RequestParam(value = "queryProCode",required = false) String proCode,
                                  @RequestParam(value ="queryProName",required = false) String proName,
                                  @RequestParam(value ="pageIndex",defaultValue = "1") Integer currentPageNo){

        logger.debug("enter ProviderController---》getProviderList method");


        //List<Provider> providerList = this.providerService.getProviderList();
        //获得供应商列表:实现分页
        List<Provider> providerList = this.providerService.getProviderList_Page(proCode,proName,currentPageNo);
        model.addAttribute("providerList",providerList);

        PageSupport pageSupport = new PageSupport();
        pageSupport.setCurrentPageNo(currentPageNo);
        pageSupport.setPageSize(Constants.pageSize);

        //通过供应商编码和供应商名称获得总记录数
        int providerCount = this.providerService.getProviderCount(proCode, proName);
        pageSupport.setTotalCount(providerCount);
        model.addAttribute("pageSupport",pageSupport);

        //保存输入框中的信息
        model.addAttribute("queryProCode",proCode);
        model.addAttribute("queryProName",proName);

        logger.debug("leave ProviderController---》getProviderList method");

        return "provider/providerlist";
    }

    /**
     * 显示添加供应商页面
     * @return provider/provideradd
     */
    @RequestMapping("/provideradd.html")
    public String addProvider(){
        logger.debug("enter ProviderController---》addProvider method");
        logger.debug("leave ProviderController---》addProvider method");
        return "provider/provideradd";
    }


    /**
     * 提交添加供应商的表单
     * @param provider
     * @param session
     * @return redirect:/sys/provider/providerlist.html
     */
    @RequestMapping("/provideraddsave.html")
    public  String saveProviderView(Provider provider, HttpSession session){
        logger.debug("enter ProviderController---》saveProviderView method");

        //创建者
        User loginUser = (User) session.getAttribute(Constants.USER_SESSION);
        //设置创建人
        provider.setCreatedBy(loginUser.getId());
        //设置创建时间-当前时间
        provider.setCreationDate(new Date());
        int count =this.providerService.addProvider(provider);
        logger.debug("leave ProviderController---》saveProviderView method");
        if(count>0){
            return "redirect:/sys/provider/providerlist.html";
        }else{
            return "provider/provideradd";
        }
    }


    /**
     * 通过id实现供应商信息查询功能
     * @param id
     * @param model
     * @return provider/providerview
     */
    @RequestMapping("/providerview.html")
    public String viewProviderById(@RequestParam("pid") Integer id,
                                   Model model){
        logger.debug("enter ProviderController---》viewProviderById method");

       Provider provider = this.providerService.getProviderById(id);
       model.addAttribute("provider",provider);

        logger.debug("leave ProviderController---》viewProviderById method");
       return "provider/providerview";
    }

    /**
     * 显示供应商修改页面
     * @return provider/providermodify
     */
    @RequestMapping("/providermodify.html")
    public String modifyProvider(@RequestParam(value = "pid") Integer pid,
                                 Model model){
        logger.debug("enter ProviderController---》modifyProvider method");

        Provider provider =this.providerService.getProviderById(pid);
        model.addAttribute("provider",provider);

        logger.debug("leave ProviderController---》modifyProvider method");
        return "provider/providermodify";
    }

    /**
     * 提交修改表单
     * @param provider
     * @return redirect:/sys/provider/providerlist.html
     */
    @RequestMapping("/providermodifysave.html")
    public String saveProviderModify(Provider provider,
                                     HttpSession session){
        logger.debug("enter ProviderController---》saveProviderModify method");

        //修改者
        User ModifyUser = (User) session.getAttribute(Constants.USER_SESSION);
        //设置修改人
        provider.setModifyBy(ModifyUser.getId());
        //设置修改时间-当前时间
        provider.setModifyDate(new Date());

        int count = this.providerService.saveModifyProvider(provider);


        logger.debug("leave ProviderController---》saveProviderModify method");
        if(count>0){
            return "redirect:/sys/provider/providerlist.html";
        }else{
            return "provider/providermodify";
        }
    }

    /**
     * 根据id删除供应商
     * @param id
     * @return redirect:/sys/provider/providerlist.html
     */
    @RequestMapping("/providerdelete.html")
    public  String delteProviderById(@RequestParam("pid")Integer id){
        logger.debug("enter ProviderController---》delteProviderById method");

        int count = this.providerService.deleteProviderById(id);

        logger.debug("enter ProviderController---》delteProviderById method");
        if(count>0){
            logger.debug("删除成功");
        }else{
            logger.debug("删除失败");
        }
        return "redirect:/sys/provider/providerlist.html";
    }
}
