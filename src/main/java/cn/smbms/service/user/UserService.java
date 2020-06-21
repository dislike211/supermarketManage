package cn.smbms.service.user;

import cn.smbms.pojo.User;

import java.util.List;


public interface UserService {


    //登陆 返回boolean 还是User
    public User login(String userCode, String userPassword);

   //获得用户列表
    public List<User> getUserList();

    //分页查询用户列表
    public List<User> getUserList_page(String userName, Integer userRole, Integer currentPageNo);

    //计算总用户记录数
    public int getUserCount();

    //获得用户总记录数--通过用户姓名和用户角色
    public int getUserCount(String userName, Integer userRole);

    //添加用户到数据库中
    public int addUser(User user);

    //根据用户id，获得用户
    public User getUserById(Integer id);

    //提交修改表单
    public int modifyByUser(User user);

    //删除用户
    public int deleteUserById(Integer id);

    //密码修改
    public Integer modifyPassword(User user);
}
