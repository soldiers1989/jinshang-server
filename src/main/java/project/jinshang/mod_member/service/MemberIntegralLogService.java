package project.jinshang.mod_member.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_member.MemberIntegralLogMapper;
import project.jinshang.mod_member.bean.MemberIntegralLog;
import project.jinshang.mod_member.bean.MemberIntegralLogExample;

import java.util.List;
@Service
public class MemberIntegralLogService {

    @Autowired
    private MemberIntegralLogMapper memberIntegralLogMapper;

    /**
     *买家查询积分详细
     */
    public PageInfo getLogs(long memberid,int pageNo,int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        MemberIntegralLog memberIntegralLog=new MemberIntegralLog();

        memberIntegralLog.setId(memberid);

        List<MemberIntegralLog> list = memberIntegralLogMapper.selectByPrimarymemberId(memberIntegralLog);

        PageInfo pageInfo = new PageInfo(list);
        return  pageInfo;
    }

}
