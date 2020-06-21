package cn.smbms.service.user;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;


@Service
public class UserServiceImpl implements UserService {

    private  static final Logger logger= Logger.getLogger(String.valueOf(UserService.class));


    //自动装配
    @Autowired
    private UserMapper userMapper;

    //登陆
    public User login(String userCode, String userPassword) {

        User user = this.userMapper.getLoinUser(userCode);
        if(user!=null){
            if(!user.getUserPassword().equals(userPassword)){
                user=null;
             }
        }
            return user;
    }

    //获得用户列表
    public List<User> getUserList() {
        List<User> userList = this.userMapper.getUserList();
        return userList;
    }

    //分页查询用户列表
    public List<User> getUserList_page(String userName, Integer userRole, Integer currentPageNo) {
        //from为记录索引  （当前页码-1）*页容量=记录索引
        int pageSize= Constants.pageSize;
        int  from=(currentPageNo-1)*pageSize;
        List<User> userList =this.userMapper.getUserList_page(userName,userRole,from,pageSize);

        return userList;
    }

    //计算用户的总记录数
    public int getUserCount() {
        return this.userMapper.getUserCount();
    }

    //获得用户总记录数--通过用户姓名和用户角色
    public int getUserCount(String userName, Integer userRole) {

       return this.userMapper.getUserCountByUserNameAndUserRole(userName,userRole);

    }

    //添加用户
    public int addUser(User user) {
       return this.userMapper.addUser(user);

    }

    //根据用户id，获得用户
    public User getUserById(Integer id) {
        return this.userMapper.getUserById(id);
    }

    //提交修改表单
    public int modifyByUser(User user) {
        return this.userMapper.modifyByUser(user);
    }

    //删除用户
    public int deleteUserById(Integer id) {
        return this.userMapper.deleteUserById(id);
    }

    @Override
    public Integer modifyPassword(User user) {

        return this.userMapper.modifyPassword(user);
    }
}
