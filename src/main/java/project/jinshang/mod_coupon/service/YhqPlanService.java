package project.jinshang.mod_coupon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_coupon.YhqPlanMapper;
import project.jinshang.mod_coupon.bean.YhqPlan;
import project.jinshang.mod_coupon.bean.YhqPlanExample;

import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/05/26
 */
@Service
public class YhqPlanService {

    @Autowired
    private YhqPlanMapper yhqPlanMapper;

    public long countByExample(YhqPlanExample example) {
        return  yhqPlanMapper.countByExample(example);
    }

    public void insertYhqPlanInfo(YhqPlan yhqPlan) {
        yhqPlanMapper.insertSelective(yhqPlan);
    }

    public YhqPlan getYhqPlanInfoById(Long id) {
        return  yhqPlanMapper.selectByPrimaryKey(id);
    }

    public void updateYhqPlanInfo(YhqPlan yhqPlan) {
        yhqPlanMapper.updateByPrimaryKeySelective(yhqPlan);
    }

    public PageInfo getListByPage(int pageNo, int pageSize, YhqPlan yhqPlan) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = yhqPlanMapper.selectObject(yhqPlan);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public void deleteYhqPlanInfoById(Long id) {
        yhqPlanMapper.deleteByPrimaryKey(id);
    }


}
