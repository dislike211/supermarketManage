package cn.smbms.controller.user;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/sys/user")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 获得用户列表
     *
     * @param model
     * @param userName
     * @param userRole
     * @param currentPageNo
     * @return user/userlist
     */
    @RequestMapping({"/userlist.html", "/user.do"})
    public String getUserList(Model model,
                              @RequestParam(value = "queryname", required = false) String userName,
                              @RequestParam(value = "queryUserRole", required = false) Integer userRole,
                              @RequestParam(value = "pageIndex", defaultValue = "1") Integer currentPageNo) {
        logger.debug("enter UserController---》getUserList method");

        //保留输入框中信息 userName,userRole
        model.addAttribute("queryUserName", userName);
        model.addAttribute("queryUserRole", userRole);


        /* List<User> userList = this.userService.getUserList();*/
        List<User> userList = this.userService.getUserList_page(userName, userRole, currentPageNo);
        model.addAttribute("userList", userList);

        //创建分页对象
        PageSupport pageSupport = new PageSupport();
        // 设置当前页码
        pageSupport.setCurrentPageNo(currentPageNo);
        //设置页容量
        pageSupport.setPageSize(Constants.pageSize);
        //设置总记录数--到表中取查询
        /*int totalCount=this.userService.getUserCount();*/
        int totalCount = this.userService.getUserCount(userName, userRole);
        pageSupport.setTotalCount(totalCount);
        //设置总页数--总记录数除以页容量?  不需要调用set方法，上述已经帮你计算了

        //pageSupport 放入model容器
        model.addAttribute("pageSupport", pageSupport);

        //获得roleList
        List<Role> roleList = this.roleService.getRoleList();
        //放入model容器中
        model.addAttribute("roleList", roleList);

        logger.debug("leave UserController---》getUserList method");

        return "user/userlist";

    }


    /**
     * 显示用户添加页面
     *
     * @return user/useradd
     */
    @RequestMapping("/useradd.html")
    public String addUser() {
        logger.debug(" UserController---》addUser method");
        return "user/useradd";

    }


    /**
     * 提交添加用户的表单
     *
     * @param user
     * @param session
     * @param attach
     * @param model
     * @return user/useradd
     */
    @RequestMapping(value = "/useraddsave.html")
    public String saveUserAdd(User user,
                              HttpSession session,
                              @RequestParam(value = "idPicPath_") MultipartFile attach,
                              Model model) {
        logger.debug("enter UserController---》saveUserAdd method");

        //分析：谁添加用户操作，谁就是创建者 ? 登陆用户
        User loginUser = (User) session.getAttribute(Constants.USER_SESSION);
        //设置创建人
        user.setCreatedBy(loginUser.getId());
        //设置创建时间-当前时间
        user.setCreationDate(new Date());


        //如果文件不为空
        if (!attach.isEmpty()) {

            String newFileName = null;

            //创建一份file对象
            String originalPath = session.getServletContext().getRealPath("statics" + File.separator + "uploadFiles");
            //文件名 文件名称+后缀
            String oldFileName = attach.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldFileName);
            if (suffix.equalsIgnoreCase("png") ||
                    suffix.equalsIgnoreCase("jpg") ||
                    suffix.equalsIgnoreCase("gif")) {
                System.out.println("该文件是个图片格式");
                long prefix = System.currentTimeMillis();
                newFileName = prefix + user.getUserName() + "." + suffix;
                File targetFile = new File(originalPath, newFileName);

                //创建该对象的目录和文件
                if (!targetFile.exists()) {
                    //根据文件对象的信息，在电脑上生成路径
                    targetFile.mkdirs();
                }

                //将attach中的数据信息，转移到该文件对象中
                try {
                    attach.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("该文件不是图片格式");
            }


            //将目标文件的路径保存到数据库中  File.separator等价于一个斜杠
            String targetPath = originalPath + File.separator + newFileName;
            user.setIdPicPath(targetPath);


        } else {
            System.out.println("该对象MultipartFile为空");
        }

        int count = this.userService.addUser(user);

        logger.info("leave UserController---》saveUserAdd method");

        if (count > 0) {//添加成功
            return "redirect:/sys/user/userlist.html";
        } else {
            return "user/useradd";//逻辑视图名
        }

    }


    //方法一：根据用户id，获得用户

//    @RequestMapping("/userview.html")
//    public String viewUser(@RequestParam(value = "uid",required = true) Integer id,
//                           Model model) {
//    logger.debug("enter UserController---》viewUser method");
//
//        User user = this.userService.getUserById(id);
//        model.addAttribute("user",user);
//    logger.debug("leave UserController---》viewUser method");
//        return "user/userview";//逻辑视图名
//
//    }


    /**
     * 方法二：根据用户id查询指定用户，注意点：路径/{liangchaowei}  ---》Integer liangchaowei  需要保持一致
     *
     * @param id
     * @param model
     * @return user/userview
     */
    @RequestMapping("/userview.html/{id}")
    public String viewUser(@PathVariable Integer id,
                           Model model) {
        logger.info("enter UserController---》viewUser method");

        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);

        logger.debug("leave UserController---》viewUser method");

        return "user/userview";//逻辑视图名
    }


    /**
     * 显示用户修改界面
     *
     * @param uid
     * @param model
     * @return user/usermodify
     */
    @RequestMapping("/usermodify.html")
    public String modifyUser(@RequestParam(value = "uid") Integer uid,
                             Model model) {
        logger.debug("enter UserController---》modifyUser method");

        User user = this.userService.getUserById(uid);
        model.addAttribute("user", user);

        logger.debug("leave UserController---》modifyUser method");

        return "user/usermodify";//逻辑视图名
    }


    /**
     * 提交修改表单
     *
     * @param user
     * @return redirect:/sys/user/userlist.html
     */
    @RequestMapping("/usermodifysave.html")
    public String saveUserModify(User user, HttpSession session) {
        logger.debug("enter UserController---》saveUserModify method");

        //修改者
        User ModifyUser = (User) session.getAttribute(Constants.USER_SESSION);
        //设置修改人
        user.setModifyBy(ModifyUser.getId());
        //设置修改时间-当前时间
        user.setModifyDate(new Date());

        int count = this.userService.modifyByUser(user);

        logger.debug("leave UserController---》saveUserModify method");
        if (count > 0) {
            return "redirect:/sys/user/userlist.html";
        } else {
            return "user/usermodify";
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return redirect:/sys/user/userlist.html
     */
    @RequestMapping("/userdelete.html")
    public String deleteUser(@RequestParam(value = "uid") Integer id) {
        logger.debug("enter UserController---》deleteUser method");

        int count = this.userService.deleteUserById(id);

        logger.debug("leave UserController---》deleteUser method");
        if (count > 0) {
            logger.info("删除成功");
        } else {
            logger.info("删除失败");
        }
        return "redirect:/sys/user/userlist.html";
    }


    /**
     * 显示修改页面
     *
     * @return /pwdmodify
     */
    @RequestMapping("/pwdmodify.html")
    public String viewModifyPassword() {
        logger.debug("enter UserController---》viewModifyPassword method");
        logger.debug("leave UserController---》viewModifyPassword method");
        return "/pwdmodify";
    }


    /**
     * 实现密码修改功能
     * @param session
     * @param oldpassword
     * @param newPassword
     * @param rnewpassword
     * @return
     */
    @RequestMapping("/modifypassword.html")
    public String modifyPassword(HttpSession session,
                                 @RequestParam("oldpassword") String oldpassword,
                                 @RequestParam("newpassword") String newPassword,
                                 @RequestParam("rnewpassword") String rnewpassword) {
        logger.debug("enter UserController---》modifyPassword method");

        User user = new User();
        User loginUser = (User) session.getAttribute(Constants.USER_SESSION);
        user.setId(loginUser.getId());
        user.setUserPassword(newPassword);
        //判断旧密码是否正确，两次新密码输入是否一样
        if (oldpassword != loginUser.getUserPassword() && newPassword != rnewpassword) {
                return "/pwdmodify";
        }else{
            Integer count = this.userService.modifyPassword(user);
            logger.debug("leave UserController---》modifyPassword method");
            if (count > 0) {
                return "/login";
            } else {
                return "/pwdmodify";
            }
        }
    }
}



