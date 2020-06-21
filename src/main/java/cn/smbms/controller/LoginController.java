package cn.smbms.controller;

import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    private  static  final Logger logger= Logger.getLogger(LoginController.class);

        //自动装配  UserService userService=new UserServiceImpl();
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/dologin.html",method = RequestMethod.POST)
    public String doLogin(@RequestParam("userCode") String userCode,
                          @RequestParam("userPassword") String userPassword,
                          HttpSession session,
                          HttpServletRequest request){
        logger.debug("enter LoginController===>doLogin method");
        //将用户编码和密码传入到数据库进行比较
        User user = this.userService.login(userCode, userPassword);
        if(user!=null){//用户存在的
          /* session.setAttribute("userSession",user);*/
            session.setAttribute(Constants.USER_SESSION,user);
            return "frame";//逻辑视图名

        }else{
            request.setAttribute(Constants.LOGIN_ERROR,"用户名或者密码是错误的，请重新登陆");
            return "login";
        }



    }

    //登陆退出
    @RequestMapping(value = {"/logout.html","/user/logout.html"})
    public String logout(HttpSession session){
        logger.debug("enter LoginController===>logout method");
        session.invalidate();
        return "login";
    }

}
