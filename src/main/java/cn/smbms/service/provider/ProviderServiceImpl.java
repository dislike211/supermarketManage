package cn.smbms.service.provider;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    //获得供应商列表
    public List<Provider> getProviderList() {
        return this.providerMapper.getProviderList();
    }

    //实现分页查询订单列表
    @Override
    public List<Provider> getProviderList_Page(String proCode, String proName, Integer currentPageNo) {
        int pageSize = Constants.pageSize;
        int from = (currentPageNo-1)*pageSize;
        List<Provider> providerList=this.providerMapper.getProviderList_Page(proCode,proName,from,pageSize);
        return providerList;
    }

    //通过供应商编码和供应商名称获得总记录数
    @Override
    public int getProviderCount(String proCode, String proName) {

        return this.providerMapper.getProviderCount(proCode,proName);
    }

    //提交添加供应商表单
    @Override
    public int addProvider(Provider provider) {
        return this.providerMapper.addProvider(provider);
    }

    //通过id实现供应商信息查询功能
    @Override
    public Provider getProviderById(Integer id) {
        return this.providerMapper.getProviderById(id);
    }

    //提交修改表单
    @Override
    public int saveModifyProvider(Provider provider) {
        return this.providerMapper.saveModifyProvider(provider);
    }

    //根据id删除供应商
    @Override
    public int deleteProviderById(Integer id) {
        return this.providerMapper.deleteProviderById(id);
    }

}
