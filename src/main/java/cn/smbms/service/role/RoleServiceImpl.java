package cn.smbms.service.role;

import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 四两数字先生（公众号/CSDN）
 */

@Service
public class RoleServiceImpl implements RoleService {

    //自动装配,默认情况下，根据什么来匹配的？ 属性(成员变量)的类型RoleMapper
    @Autowired
    private RoleMapper roleMapper;

    //获得角色列表
    public List<Role> getRoleList() {
       return this.roleMapper.getRoleList();

    }
}
