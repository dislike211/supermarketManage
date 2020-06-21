package cn.smbms.interceptor;

import cn.smbms.tools.Constants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: 四两数字先生（公众号/CSDN）
 */
public class SysInterceptor extends HandlerInterceptorAdapter {

    //预处理 pre 之前 调用处理器之前执行的方法
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute(Constants.USER_SESSION);
        if(loginUser!=null){//登陆成功的
            return true;//放行通过
        }else {
            //页面跳转：请求转发或者响应重定向
            request.getRequestDispatcher("/401.jsp").forward(request,response);//注意路径

          /*  response.sendRedirect(request.getContextPath()+"/401.jsp");//绝对路径*/
            System.out.println("非法访问，禁止通过");
            return false;//拦截下来，禁止通过
        }


    }
}
