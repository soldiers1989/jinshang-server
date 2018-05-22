package project.jinshang.mod_product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.jinshang.mod_product.MemberOperateLogMapper;
import project.jinshang.mod_product.bean.MemberOperateLog;

@Service
public class MemberOperateLogService {

    @Autowired
    private MemberOperateLogMapper memberOperateLogMapper;

    /**
     * 保存用户操作日志
     * @param memberOperateLog
     */
    public void saveMemberOperateLog(MemberOperateLog memberOperateLog){
        memberOperateLogMapper.insertSelective(memberOperateLog);
    }
}
