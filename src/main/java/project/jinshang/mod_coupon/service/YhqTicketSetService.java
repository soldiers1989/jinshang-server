package project.jinshang.mod_coupon.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_coupon.YhqTicketsetMapper;
import project.jinshang.mod_coupon.bean.YhqTicketset;
import project.jinshang.mod_coupon.bean.YhqTicketsetExample;

import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/05/26
 */
@Service
public class YhqTicketSetService {
    @Autowired
    private YhqTicketsetMapper yhqTicketsetMapper;

    public long countByExample(YhqTicketsetExample yhqTicketsetExample) {
        return  yhqTicketsetMapper.countByExample(yhqTicketsetExample);
    }

    public void insertYhqTicketSetInfo(YhqTicketset yhqTicketset) {
        yhqTicketsetMapper.insertSelective(yhqTicketset);
    }


    public YhqTicketset getYhqTicketSetInfoById(Long id) {
        return  yhqTicketsetMapper.selectByPrimaryKey(id);
    }

    public void updateYhqTicketSetInfo(YhqTicketset yhqTicketset) {
        yhqTicketsetMapper.updateByPrimaryKeySelective(yhqTicketset);
    }

    public void deleteYhqTicketSetInfoById(Long id) {
        yhqTicketsetMapper.deleteByPrimaryKey(id);
    }

    public PageInfo getListByPage(int pageNo, int pageSize, YhqTicketset yhqTicketset) {
        if(pageNo != -1){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String,Object>> list = yhqTicketsetMapper.selectByObject(yhqTicketset);

        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;

    }

    public YhqTicketset selectById(long ticketid1) {
        return yhqTicketsetMapper.selectByPrimaryKey(ticketid1);
    }

    public void updateSurpluscount(Long id, Long surpluscount1) {
        yhqTicketsetMapper.updateSurpluscount(id,surpluscount1);
    }
}
