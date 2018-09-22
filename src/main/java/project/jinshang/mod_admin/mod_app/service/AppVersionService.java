package project.jinshang.mod_admin.mod_app.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_admin.mod_app.AppVersionMapper;
import project.jinshang.mod_admin.mod_app.bean.AppVersion;
import project.jinshang.mod_admin.mod_app.bean.AppVersionExample;

import java.util.List;
import java.util.Map;

@Service
public class AppVersionService {

    @Autowired
    private AppVersionMapper appVersionMapper;

    public long countByExample(AppVersionExample example) {
        return  appVersionMapper.countByExample(example);
    }

    public void insertAppVersionInfo(AppVersion appVersion) {
        appVersionMapper.insertSelective(appVersion);
    }


    public AppVersion getDetailById(Long id) {
        return appVersionMapper.selectByPrimaryKey(id);
    }

    public void updateAppVersionInfo(AppVersion appVersion) {
        appVersionMapper.updateByPrimaryKeySelective(appVersion);
    }

    public void deleteAppVersionById(Long id) {
        appVersionMapper.deleteByPrimaryKey(id);
    }

    public PageInfo getListByPage(int pageNo, int pageSize, AppVersion appVersion) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String,Object>> list = appVersionMapper.selectByObject(appVersion);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    public void updateVersionOrVersionName(AppVersion appVersion) {
    appVersionMapper.updateVersionOrVersionName(appVersion);
    }

    public AppVersion checkversionList(AppVersion appVersion) {
        //version不能传入因为app是传进来他们当前旧版本，后台拿过来会当作搜索 导致搜索不到
       return appVersionMapper.selectByAppVersion(appVersion);
    }
}
