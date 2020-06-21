package cn.smbms.service.provider;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderService {

    //获得供应商列表
    public List<Provider> getProviderList();

    //获得供应商列表:实现分页
    public List<Provider> getProviderList_Page(String proCode, String proName, Integer currentPageNo);

    //通过供应商编码和供应商名称获得总记录数
    public int getProviderCount(String proCode, String proName);

    //提交添加供应商表单
    public int addProvider(Provider provider);

    //通过id实现供应商信息查询功能
    public Provider getProviderById(Integer id);

    //提交修改表单
    public int saveModifyProvider(Provider provider);

    //根据id删除供应商
    public int deleteProviderById(Integer id);

}
