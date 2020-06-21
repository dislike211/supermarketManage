package cn.smbms.dao.provider;

import cn.smbms.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {

    //获得providerList供应商列表
    public List<Provider> getProviderList();

    //实现分页查询获得用户列表
    public List<Provider> getProviderList_Page(@Param("proCode") String proCode,
                                               @Param("proName") String proName,
                                               @Param("from") int from,
                                               @Param("pageSize") int pageSize);

    //通过供应商编码和供应商名称获得总记录数
    public int getProviderCount(@Param("proCode") String proCode,
                                @Param("proName") String proName);

    //提交添加供应商表单
    public  int addProvider(Provider provider);

    //通过id实现供应商信息查询功能
    public Provider getProviderById(@Param("id") Integer id);

    //提交修改的表单
    public int saveModifyProvider(Provider provider);

    //根据id删除供应商
    public int deleteProviderById(@Param("id") Integer id);

}
