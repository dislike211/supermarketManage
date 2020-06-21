package cn.smbms.dao.user;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface UserMapper {

    //登陆 第一种写法
  /*  public User getLoginUser(String userCode,String userPassword);*/

    //登陆 第二种写法
    public User getLoinUser(@Param("userCode") String userCode);

    //获得用户列表
    public List<User>  getUserList();

    //分页查询用户列表
  public  List<User> getUserList_page(@Param("userName") String userName,
                                      @Param("userRole") Integer userRole,
                                      @Param("from") int from,
                                      @Param("pageSize") int pageSize);
    //获得总记录数
    public int getUserCount();

    //获得用户总记录数--通过用户姓名和用户角色
    public int getUserCountByUserNameAndUserRole(@Param("userName") String userName, @Param("userRole") Integer userRole);

    //添加用户
    public int addUser(User user);

    //根据用户id，获得用户
    public User getUserById(@Param("id") Integer id);

    //提交修改表单
    public int modifyByUser(User user);

    //删除用户
    public int deleteUserById(@Param("id") Integer id);

    //免密修改
    public Integer modifyPassword(User user);
}
