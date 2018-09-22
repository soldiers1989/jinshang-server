package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.ReturnVisitMapper;
import project.jinshang.mod_member.bean.ReturnVisit;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * create : zzy
 * date : 2018/07/13
 */
@Service
public class ReturnVisitService {

    @Autowired
    private ReturnVisitMapper returnVisitMapper;

    public void insertReturnVisitInfo(ReturnVisit returnVisit) {
        returnVisitMapper.insertSelective(returnVisit);
    }

    public PageInfo getList(String mobile,String waysalesman,String username,String labelname,Date startime, Date endtime, ReturnVisit returnVisit, int pageNo, int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = returnVisitMapper.selectObject(mobile,waysalesman,username,labelname,startime, endtime,returnVisit);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public ReturnVisit selectById(Long id) {
        return returnVisitMapper.selectByPrimaryKey(id);
    }

    public void updateReturnVisitInfo(ReturnVisit returnVisit) {
        returnVisitMapper.updateByPrimaryKey(returnVisit);
    }

    public void deleteById(Long id) {
        returnVisitMapper.deleteByPrimaryKey(id);
    }

    public PageInfo getListByMemeberid(long memberid,int pageNo, int pageSize) {
        if (pageNo != -1) {
            PageHelper.startPage(pageNo, pageSize);
        }
        List<Map<String, Object>> list = returnVisitMapper.getListByMemeberid(memberid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public void insertSelective(ReturnVisit returnVisit) {
        returnVisitMapper.insertSelective(returnVisit);
    }
}
