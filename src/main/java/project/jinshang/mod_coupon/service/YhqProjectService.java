package project.jinshang.mod_coupon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_coupon.YhqProjectMapper;
import project.jinshang.mod_coupon.bean.YhqProject;
import project.jinshang.mod_coupon.bean.YhqProjectExample;

import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/05/26
 */
@Service
public class YhqProjectService {
    @Autowired
    private YhqProjectMapper yhqProjectMapper;

    public PageInfo getListByPage(int pageNo, int pageSize, YhqProject yhqProject) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<Map<String, Object>> list = yhqProjectMapper.selectObject(yhqProject);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public void insertYhqProjectInfo(YhqProject yhqProject) {
        yhqProjectMapper.insertSelective(yhqProject);
    }

    public YhqProject getYhqProjectInfoById(Long id) {
        return yhqProjectMapper.selectByPrimaryKey(id);
    }

    public void updateYhqProjectInfo(YhqProject yhqProject) {
        yhqProjectMapper.updateByPrimaryKeySelective(yhqProject);
    }

  /*  public PageInfo getListByPage1(int pageNo, int pageSize, YhqProject yhqProject) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo,pageSize);
        }
        List<Map<String, Object>> list = yhqProjectMapper.selectObject1(yhqProject);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }*/

    public long countByExample(YhqProjectExample yhqProjectExample) {
        return  yhqProjectMapper.countByExample(yhqProjectExample);
    }

    public void deleteYhqProjectInfoById(Long id) {
        yhqProjectMapper.deleteByPrimaryKey(id);
    }
}
